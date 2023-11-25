/*
 * SharedAspect
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Classe principale che rappresenta l'aspetto condiviso
public class SharedAspect {

    // Metodo per attaccare un oggetto a un aspetto condiviso
    public static <T> T attach(T target) {
    	
        // Verifica se l'oggetto da attaccare non è nullo
        if (target == null)
            throw new IllegalArgumentException("target == null");

        // Ottiene la classe dell'oggetto target
        Class<?> targetClass = target.getClass();

        // Ottiene le interfacce implementate dall'oggetto target
        Class<?>[] targetInterfaces = targetClass.getInterfaces();

        // Crea un proxy dinamico che implementa le interfacce dell'oggetto target
        Object proxy = Proxy.newProxyInstance(targetClass.getClassLoader(), targetInterfaces,
                new InnerInvocationHandler(target));

        // Esegue un cast dell'oggetto proxy al tipo desiderato
        @SuppressWarnings("unchecked")
        T result = (T) proxy;

        // Restituisce l'oggetto proxy attaccato all'aspetto condiviso
        return result;
    }

    // Classe interna che funge da gestore delle chiamate ai metodi sull'oggetto proxy
    private static class InnerInvocationHandler implements InvocationHandler {
        
    	// Oggetto target a cui le chiamate ai metodi saranno delegate
        private Object target;

        // Oggetto di sincronizzazione per garantire l'accesso sincronizzato all'oggetto target
        private Object lock;

        // Costruttore che inizializza l'oggetto target e il lock di sincronizzazione
        private InnerInvocationHandler(Object target) {
            this.target = target;

            // Utilizza un oggetto separato come lock di sincronizzazione
            // per evitare problemi legati a lock imprevisti
            this.lock = new Object();
        }

        // Metodo chiamato quando viene invocato un metodo sull'oggetto proxy
        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            try {
                
            	// Utilizza il lock per garantire l'accesso sincronizzato all'oggetto target
                synchronized (lock) {
                
                	// Invoca il metodo sul target originale e ottiene il risultato
                    Object result = method.invoke(target, arguments);

                    // Restituisce il risultato ottenuto
                    return result;
                }
            } catch (InvocationTargetException exception) {
                
            	// Gestisce eventuali eccezioni lanciate all'interno del metodo invocato
                throw exception.getCause();
            } catch (Throwable throwable) {
                
            	// Gestisce altre eccezioni
                throw throwable;
            }
        }
    }
}
