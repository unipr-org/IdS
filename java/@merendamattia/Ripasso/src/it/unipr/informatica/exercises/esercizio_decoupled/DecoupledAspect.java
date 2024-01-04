package it.unipr.informatica.exercises.esercizio_decoupled;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.time.Instant;

public class DecoupledAspect {
	@SuppressWarnings("unchecked")
	public static <T> T attach(Object target) {
		if(target == null)
			throw new IllegalArgumentException("target == null");
		
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		
		return (T) proxy;
	}
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;
		
		public InnerInvocationHandler(Object target) {
			this.target = target;
		}
		
		public void log(String msg) {
			synchronized (target) {
				Timestamp now = Timestamp.from(Instant.now());
				System.out.printf("[%s - %s] %s \n", now, Thread.currentThread(), msg);
			}
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			SimpleFuture future = new SimpleFuture();
			
			new Thread(() -> {
				try {
					log("IN " + method.getName() + " - Args = " + args);
					
					Object result = method.invoke(target, args);
					
					log("OUT " + method.getName() + " - Result = " + result);
					
					future.setValue(result);
				} catch (Throwable e) {
					future.setException(e);
				}
			}).start();
			
			return future.get();
		}
		
	} // ! InnerInvocationHandler
} // ! DecoupledAspect
