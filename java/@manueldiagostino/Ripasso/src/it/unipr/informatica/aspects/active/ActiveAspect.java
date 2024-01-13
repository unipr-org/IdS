package it.unipr.informatica.aspects.active;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import it.unipr.informatica.aspects.active.pools.Callback;
import it.unipr.informatica.aspects.active.pools.Future;
import it.unipr.informatica.aspects.active.pools.ThreadPool;
import it.unipr.informatica.aspects.active.pools.ThreadPoolsHandler;
import it.unipr.informatica.executors.SimpleFuture;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ActiveAspect {
	/*
	 * Restituisco un ActiveHandler<A> res dove A è l'interfaccia da rendere attiva;
	 * tramite res.get() si ottiene il proxy su cui fare tutte le chiamate esposte dall'interfaccia A.
	 */
	public static <T, A extends Active<T>> ActiveHandler<A> attach(Class<A> activeInterface, T target) {
		if (target == null)
			throw new IllegalArgumentException("target == null");
		if (activeInterface == null)
			throw new IllegalArgumentException("activeInterface == null");
		
		Class<?> clazz = target.getClass();
		InnerInvocationHandler handler = new InnerInvocationHandler(target);
		
		@SuppressWarnings("unchecked")
		A proxy = (A) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {activeInterface}, handler);
		
		return new InnerActiveHandler<A>(proxy, handler);
	}
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;
		private ThreadPool threadPool;
		
		public InnerInvocationHandler(Object target) {
			this.target = target;
			this.threadPool = ThreadPoolsHandler.getNewThreadPool(10);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Object invoke(Object invoker, Method method, Object[] args) throws Throwable {
			int parametersCount = args.length;
			
//			Runnable runnable = () -> {
//				try {
//					method.invoke(target, args);
//				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			};
			
			if (parametersCount == 2) {
				--parametersCount;
				
				Callback<Object> callback = (Callback<Object>) args[parametersCount];
//				threadPool.submit(runnable, callback);
				threadPool.submit(() -> method.invoke(target, args), callback);
				return null;
			} 
			
//			Future<?> future = threadPool.submit(runnable);
			Future<?> future = threadPool.submit(() -> method.invoke(target, args));
			return future;
		}
		
		public void shutdown() {
			threadPool.shutdown();
		}
		
	}
	
	private static class InnerActiveHandler<A extends Active<?>> implements ActiveHandler<A> {
		private A proxy;
		private InnerInvocationHandler handler;
		
		public InnerActiveHandler(A proxy, InnerInvocationHandler handler) {
			this.proxy = proxy;
			this.handler = handler;
		}
		
		@Override
		public A get() {
			synchronized (handler) {
				return proxy;
			}
		}

		@Override
		public void shutdown() {
			handler.shutdown();
		}
		
	}	
}
