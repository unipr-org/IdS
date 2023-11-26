/*
 * ActiveAspect
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import it.unipr.informatica.aspects.interfaces.Active;
import it.unipr.informatica.aspects.interfaces.ActiveHandler;
import it.unipr.informatica.concurrent.pool.Callback;
import it.unipr.informatica.concurrent.pool.ExecutorService;
import it.unipr.informatica.concurrent.pool.Executors;

public class ActiveAspect {
    
	// Metodo per allegare un'implementazione di ActiveInterface a un oggetto
    public static <T, A extends Active<T>> ActiveHandler<A> attach(Class<A> activeInterface, T target) {
        return attach(activeInterface, target, 10);
    }

    // Metodo per allegare un'implementazione di ActiveInterface a un oggetto con un pool di dimensione specifica
    public static <T, A extends Active<T>> ActiveHandler<A> attach(Class<A> activeInterface, T target, int poolSize) {
        if (activeInterface == null)
            throw new IllegalArgumentException("activeInterface == null");

        if (target == null)
            throw new IllegalArgumentException("target == null");

        if (poolSize < 1)
            throw new IllegalArgumentException("poolSize < 1");

        // Creazione di un'istanza di InnerInvocationHandler
        InnerInvocationHandler invocationHandler = new InnerInvocationHandler(target, poolSize);

        // Creazione di un proxy per l'interfaccia ActiveInterface
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class<?>[] { activeInterface },
                invocationHandler);

        // Creazione di un'istanza di InnerActiveHandler
        @SuppressWarnings("unchecked")
        A object = (A) proxy;

        return new InnerActiveHandler<A>(object, invocationHandler);
    }

    // Classe interna che gestisce il proxy e il suo InvocationHandler
    // Fornisce un'interfaccia più pulita e semantica per accedere al proxy di Active<T>
    private static class InnerActiveHandler<A extends Active<?>> implements ActiveHandler<A> {
        private A proxy;

        private InnerInvocationHandler handler;

        // Costruttore
        private InnerActiveHandler(A proxy, InnerInvocationHandler handler) {
            this.proxy = proxy;

            this.handler = handler;
        }

        // Restituisce il proxy
        @Override
        public A get() {
            return proxy;
        }

        // Ferma l'esecuzione dei thread asincroni
        @Override
        public void shutdown() {
            handler.shutdown();
        }
    }

    // Classe interna che funge da InvocationHandler per il proxy
    private static class InnerInvocationHandler implements InvocationHandler {
        private ExecutorService executorService; // Servizio di esecuzione asincrona
        private Object target; // Oggetto su cui eseguire i metodi

        // Costruttore
        private InnerInvocationHandler(Object target, int poolSize) {
            this.target = target;
            this.executorService = Executors.newFixedThreadPool(poolSize);
        }

        // Ferma l'esecuzione dei thread asincroni
        private void shutdown() {
            executorService.shutdown();
        }

        // Metodo chiamato quando un metodo viene invocato sull'oggetto proxy
        @Override
        public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
            Class<?>[] parameterTypes = method.getParameterTypes();
            
            int parameterCount = parameterTypes.length;
            
            Class<?> targetClass = target.getClass();

            // Se l'ultimo parametro del metodo è di tipo Callback, esegui il metodo in modo asincrono
            if (parameterCount > 0 && parameterTypes[parameterCount - 1] == Callback.class) {
                
            	parameterCount--;
                
            	Class<?>[] newParameterTypes = (Class<?>[]) Arrays.copyOf(parameterTypes, parameterCount);
                
            	Method passiveMethod = targetClass.getMethod(method.getName(), newParameterTypes);
                
            	@SuppressWarnings("unchecked")
                Callback<Object> callback = (Callback<Object>) arguments[parameterCount];
                
            	Object[] newArguments = Arrays.copyOf(arguments, parameterCount);

                // Esegui il metodo in modo asincrono e fornisce il risultato al callback
                executorService.submit(() -> invokeMethod(passiveMethod, newArguments), callback);

                return null;
            
            } else {
                // Se il metodo non ha un parametro di tipo Callback, esegui il metodo in modo asincrono e restituisci il Future
                Method passiveMethod = targetClass.getMethod(method.getName(), parameterTypes);
                
                return executorService.submit(() -> invokeMethod(passiveMethod, arguments));
            }
        }

        // Invoca il metodo passivo sull'oggetto target
        private Object invokeMethod(Method passiveMethod, Object[] arguments) throws Exception {
            try {
                return passiveMethod.invoke(target, arguments);
                
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();

                if (cause instanceof RuntimeException)
                    throw (RuntimeException) cause;

                if (cause instanceof Exception)
                    throw (Exception) cause;

                throw exception;
            }
        }
    }
}
