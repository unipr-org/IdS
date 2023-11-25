/*
 * BeanLoader
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class BeanLoader {
    
    public <T extends Bean> List<T> load(Class<T> clazz, String fileName) throws IOException {
        
    	// Verifica se la classe è un'interfaccia
        if (clazz == null || !clazz.isInterface())
            throw new IllegalArgumentException("clazz == null || !clazz.isInterface()");

        if (fileName == null || fileName.length() == 0)
            throw new IllegalArgumentException("fileName == null || fileName.length() == 0");

        try (	InputStream inputStream = new FileInputStream(fileName);
        		Scanner scanner = new Scanner(inputStream)) {
            scanner.useLocale(Locale.US);

            String heading = scanner.nextLine();

            // Ottiene i nomi delle proprietà dalla riga di intestazione
            String[] propertyNames = split(heading);

            // Ottiene i tipi delle proprietà dalla classe Bean
            Class<?>[] propertyTypes = getPropertyTypes(clazz, propertyNames);

            // Lista risultante degli oggetti Bean
            List<T> result = new ArrayList<T>();

            // Ciclo per leggere le righe del file e creare gli oggetti Bean
            while (scanner.hasNext()) {
            	
                // Oggetto Value utilizzato come contenitore per i valori delle proprietà
                Value container = new Value();

                // Creazione del proxy Bean dinamico
                // Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
                
                @SuppressWarnings("unchecked")
                T bean = (T) Proxy.newProxyInstance(
                        clazz.getClassLoader(),
                        new Class<?>[]{clazz},
                        (proxy, method, args) -> {
                            // InvocationHandler
                        	
                        	// Gestisce le chiamate ai metodi dell'interfaccia
                            String methodName = method.getName();

                            // Se il metodo inizia con "get" e non ha argomenti, restituisce il valore dalla mappa
                            if ((args != null && args.length > 0) || !methodName.startsWith("get") || methodName.length() <= 3)
                                return method.invoke(container, args);

                            // Altrimenti, estrae il nome della proprietà e restituisce il valore associato
                            String propertyName = methodName.substring(3);
                            return container.get(propertyName);
                        }
                );

                // Legge una riga del file e ottiene i valori delle proprietà
                String line = scanner.nextLine();
                String[] values = split(line);

                // Verifica che il numero di valori sia coerente con il numero di proprietà
                if (propertyNames.length != values.length)
                    throw new IOException("invalid file format");

                // Ciclo per assegnare i valori alle proprietà dell'oggetto Value
                for (int i = 0; i < propertyTypes.length; ++i) {
                    Object value = fromString(values[i], propertyTypes[i]);

                    // Assegna il valore all'oggetto Value
                    container.put(propertyNames[i], value);
                }

                // Aggiunge l'oggetto Bean alla lista risultante
                result.add(bean);
            }

            // Restituisce la lista degli oggetti Bean
            return result;
        } catch (IOException exception) {
            // Rilancia l'eccezione IOException
            throw exception;
        } catch (Throwable throwable) {
            // Rilancia un'eccezione IOException con la causa
            throw new IOException(throwable);
        }
    }

    // Converte una stringa nel tipo specificato
    protected Object fromString(String text, Class<?> clazz) {
        if (clazz == String.class)
            return text;

        if (clazz == Integer.TYPE)
            return Integer.parseInt(text);

        if (clazz == Float.TYPE)
            return Float.parseFloat(text);

        if (clazz == Double.TYPE)
            return Double.parseDouble(text);

        // Se il tipo non è gestito, lancia un'eccezione
        throw new IllegalArgumentException("cannot convert " + text + " to " + clazz.getName());
    }

    // Ottiene i tipi delle proprietà della classe Bean dai loro nomi
    private <T> Class<?>[] getPropertyTypes(Class<T> clazz, String[] propertyNames) throws IOException {
        // Array per i tipi delle proprietà
        Class<?>[] propertyTypes = new Class<?>[propertyNames.length];

        // Ciclo per ottenere i tipi delle proprietà dalla classe Bean
        for (int i = 0; i < propertyNames.length; ++i) {
            String propertyName = propertyNames[i];

            // Costruisce il nome del metodo "get" corrispondente alla proprietà
            String methodName = "get" + propertyName;

            try {
                // Ottiene il metodo dalla classe Bean
                Method method = clazz.getMethod(methodName);

                // Ottiene il tipo di ritorno del metodo e lo assegna all'array
                propertyTypes[i] = method.getReturnType();
            } catch (NoSuchMethodException exception) {
                // Se il metodo non esiste, lancia un'eccezione
                throw new IOException("invalid property name " + propertyName);
            }
        }

        // Restituisce l'array dei tipi delle proprietà
        return propertyTypes;
    }

    // Suddivide una stringa in un array di valori
    private String[] split(String line) {
        // Suddivide la stringa utilizzando il carattere di tabulazione come delimitatore
        String[] values = line.split("\t");

        // Rimuove gli spazi bianchi dai valori
        for (int i = 0; i < values.length; ++i)
            values[i] = values[i].trim();

        // Restituisce l'array dei valori
        return values;
    }

    // Classe Value utilizzata come contenitore per i valori delle proprietà
    private static final class Value {
        private Map<String, Object> data = new HashMap<>();

        // Ottiene il valore associato a una proprietà
        private Object get(String propertyName) {
            Object result = data.get(propertyName);

            // Se il valore è nullo, lancia un'eccezione
            if (result == null)
                throw new IllegalStateException("result == null");

            // Restituisce il valore
            return result;
        }

        // Assegna un valore a una proprietà
        private void put(String propertyName, Object value) {
            // Se il valore è nullo, lancia un'eccezione
            if (value == null)
                throw new IllegalArgumentException("value == null");

            // Se il nome della proprietà è nullo o vuoto, lancia un'eccezione
            if (propertyName == null || propertyName.length() == 0)
                throw new IllegalArgumentException("propertyName == null || propertyName.length() == 0");

            // Assegna il valore alla mappa
            data.put(propertyName, value);
        }

        // Override dei metodi equals, hashCode, e toString
        @Override
        public boolean equals(Object other) {
            // Confronta le mappe di dati per determinare l'uguaglianza
            if (!(other instanceof Value))
                return false;

            Value otherValue = (Value) other;
            return data.equals(otherValue.data);
        }

        @Override
        public int hashCode() {
            // Utilizza l'hashCode della mappa di dati
            return data.hashCode();
        }

        @Override
        public String toString() {
            // Utilizza la rappresentazione stringa della mappa di dati
            return data.toString();
        }
    }
}
