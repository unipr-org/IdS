package it.unipr.informatica.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BeanLoader {
	
	/*
	 * La dichiarazione <T extends Bean> è una bounded type parameter (parametro di tipo limitato) in Java e 
	 * indica che il tipo generico T deve estendere la classe Bean o implementare l'interfaccia Bean.
	 */
	
	// Accetta due parametri: il tipo della classe Bean (clazz) e il nome del file (fileName) da cui caricare i dati.
	public <T extends Bean> List<T> load(Class<T> clazz, String fileName) throws IOException {
		
		// try-catch con risorse: Apre un flusso di input (InputStream) e un Scanner per leggere il file.
		try (
				InputStream inputStream = new FileInputStream(fileName); 
				Scanner scanner = new Scanner(inputStream)
						) {
			scanner.useLocale(Locale.US);

			// La prima riga del file viene letta per ottenere i nomi delle proprietà del Bean.
			String heading = scanner.nextLine();
			String[] propertyNames = split(heading);
			
			// Viene ottenuto un array di tipi di proprietà (propertyTypes) in base ai nomi delle proprietà.
			Class<?>[] propertyTypes = getPropertyTypes(clazz, propertyNames);

			List<T> result = new ArrayList<T>();

			// Legge il file riga per riga, crea un'istanza di Bean, e utilizza la riflessione per 
			// impostare i valori delle proprietà.
			while (scanner.hasNext()) {
				// Creo una nuova istanza della classe clazz
				T bean = clazz.getConstructor().newInstance();

				// Leggo la riga del file
				String line = scanner.nextLine();

				// Splitto i valori (separati da un tab /t) in un array di stringhe
				String[] values = split(line);

				if (propertyNames.length != values.length)
					throw new IOException("invalid file format");

				for (int i = 0; i < propertyNames.length; ++i) {
					// Per ogni proprieta' della classe creo il relativi setter e lo invoco sull'oggetto bean
					Method method = clazz.getMethod("set" + propertyNames[i], propertyTypes[i]);

					// Ottengo il valore "parsed" pronto per essere settato
					Object value = fromString(values[i], propertyTypes[i]);

					// Invoco il setter
					method.invoke(bean, value);
				}
				// Aggiungo l'elemento in coda alla lista dei bean creati
				result.add(bean);
			}
			// Restituisce una lista di oggetti Bean creati dal file.
			return result;
		
		} catch (IOException exception) {
			throw exception;
		} catch (Throwable throwable) {
			throw new IOException(throwable);
		}
	}

	/*
	 * text: la stringa che deve essere convertita.
	 * clazz: la classe che rappresenta il tipo di dati in cui la stringa deve essere convertita.
	 */
	protected Object fromString(String text, Class<?> clazz) {
		if (clazz == String.class)
			return text;

		if (clazz == Integer.TYPE)
			return Integer.parseInt(text);

		if (clazz == Float.TYPE)
			return Float.parseFloat(text);

		if (clazz == Double.TYPE)
			return Double.parseDouble(text);

		throw new IllegalArgumentException("cannot convert " + text + " to " + clazz.getName());
	}
	
	// Ottiene i tipi delle proprietà del Bean basandosi sui nomi delle proprietà.
	// Utilizza la riflessione per cercare i metodi "setter" corrispondenti ai nomi delle proprietà nella classe Bean.
	// Verifica se il metodo ha un solo parametro (il tipo di proprietà) e nessun valore di ritorno, e ne ottiene il tipo di parametro.
	private <T> Class<?>[] getPropertyTypes(Class<T> clazz, String[] propertyNames) throws IOException {
		
		// Ottiene un array di tutti i metodi pubblici della classe clazz, che è la classe associata al tipo generico T.
		Method[] methods = clazz.getMethods();

		// Crea un array di oggetti Class<?> della stessa lunghezza dell'array di nomi delle proprietà.
		Class<?>[] propertyTypes = new Class<?>[propertyNames.length];

		// Avvia un ciclo che attraversa tutti i nomi delle proprietà forniti come argomento.
		for (int i = 0; i < propertyNames.length; ++i) {
			
			// Estrae il nome della proprietà corrente dal array di nomi delle proprietà.
			String propertyName = propertyNames[i];

			// Genera il nome del metodo "setter" corrispondente al nome della proprietà.
			String methodName = "set" + propertyName;

			// Avvia un secondo ciclo che attraversa tutti i metodi della classe Bean.
			for (int j = 0; j < methods.length; ++j) {
				
				// Estrae il metodo corrente dalla classe Bean.
				Method method = methods[j];

				// Verifica se il metodo ha un solo parametro (indicativo del tipo di proprietà), 
				// ha un tipo di ritorno void (indicativo di un "setter"), 
				// e se il nome del metodo corrisponde al nome del metodo generato.
				if (method.getParameterCount() == 1 
						&& method.getReturnType() == Void.TYPE
						&& methodName.equals(method.getName())
						) {
					
					// Estrae il tipo del parametro del metodo (che rappresenta il tipo della proprietà) 
					// e lo inserisce nell'array propertyTypes alla posizione corrispondente.
					Class<?>[] parameterTypes = method.getParameterTypes();
					propertyTypes[i] = parameterTypes[0];

					break;
				}
			}

			// Verifica se il tipo della proprietà è stato trovato. Se non lo è, lancia un'eccezione, 
			// poiché significa che non esiste un metodo "setter" corrispondente al nome della proprietà.
			if (propertyTypes[i] == null)
				throw new IOException("invalid property name " + propertyName);
		}

		// Restituisce l'array propertyTypes, che contiene i tipi delle proprietà in base ai nomi forniti.
		return propertyTypes;
	}

	private String[] split(String line) {
		String[] values = line.split("\t");

		for (int i = 0; i < values.length; ++i)
			values[i] = values[i].trim();

		return values;
	}
}
