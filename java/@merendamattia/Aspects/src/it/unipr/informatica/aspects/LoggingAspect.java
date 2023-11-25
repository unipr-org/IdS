/*
 * LoggingAspect
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class LoggingAspect {

    // Formato per la data nei messaggi di log
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,SSS");

    // Metodo per aggiungere l'aspetto di logging a un oggetto target
    public static <T> T attach(T target) {
        if (target == null)
            throw new IllegalArgumentException("target == null");

        // Ottiene la classe dell'oggetto target
        Class<?> targetClass = target.getClass();

        // Ottiene le interfacce implementate dalla classe dell'oggetto target
        Class<?>[] targetInterfaces = targetClass.getInterfaces();

        // Crea un proxy dinamico che implementa le stesse interfacce dell'oggetto target
        // Utilizza un InnerInvocationHandler per gestire le invocazioni dei metodi
        Object proxy = Proxy.newProxyInstance(targetClass.getClassLoader(), targetInterfaces,
                new InnerInvocationHandler(target));

        // Casta l'oggetto proxy al tipo T e lo restituisce
        @SuppressWarnings("unchecked")
        T result = (T) proxy;

        return result;
    }

    // Metodo per registrare messaggi di log
    private static void log(String message) {
        // Formatta l'orario attuale
        String now = DATE_FORMAT.format(System.currentTimeMillis());

        // Sincronizza l'accesso alla stampa del messaggio di log
        synchronized (DATE_FORMAT) {
            // Stampa il messaggio di log con data, ora, nome del thread e il messaggio effettivo
            System.out.printf("[%s %s] %s\n", now, Thread.currentThread().getName(), message);
        }
    }

    // Classe interna che implementa InvocationHandler per gestire le invocazioni dei metodi
    private static class InnerInvocationHandler implements InvocationHandler {

        // Oggetto target reale al quale verranno inoltrate le chiamate ai metodi
        private Object target;

        // Costruttore che inizializza l'oggetto target
        private InnerInvocationHandler(Object target) {
            this.target = target;
        }

        // Metodo chiamato quando viene invocato un metodo sull'oggetto proxy
        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            // Costruisce una stringa che rappresenta il nome completo del metodo
            String name = target.getClass().getName() + "." + method.getName();

            try {
                // Registra un messaggio di log indicando l'inizio dell'esecuzione del metodo
                log("In  " + name + " " + Arrays.toString(arguments));

                // Invoca il metodo effettivo sull'oggetto target
                Object result = method.invoke(target, arguments);

                // Registra un messaggio di log indicando la fine dell'esecuzione del metodo
                log("Out " + name + " " + result);

                // Restituisce il risultato ottenuto
                return result;
                
            } catch (InvocationTargetException exception) {
                // Gestisce eventuali eccezioni lanciate all'interno del metodo invocato
                Throwable cause = exception.getCause();

                // Registra un messaggio di log indicando l'eccezione
                log("Out " + name + " " + cause.getMessage());

                // Rilancia l'eccezione
                throw cause;
                
            } catch (Throwable throwable) {
                // Gestisce altre eccezioni
                // Registra un messaggio di log indicando l'eccezione
                log("Out " + name + " " + throwable.getMessage());

                // Rilancia l'eccezione
                throw throwable;
            }
        }
    }
}
