package it.unipr.informatica.exercises.esercizio1_4_2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.time.Instant;

public class LoggingAspect {
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(T target) {
		if(target == null)
			throw new IllegalArgumentException("target == null");
		
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		
		return (T) proxy;
	}
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;
		private Object mutex;
		private Thread currentThread;
		
		public InnerInvocationHandler(Object target) {
			this.target = target;
			this.mutex = new Object();
			this.currentThread = null;
		}
		
		private void log(String msg) {
			synchronized (mutex) {
				Timestamp now = Timestamp.from(Instant.now()); 
				System.out.println("[" + now + " - " + currentThread + "] " + msg);
			}
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			currentThread = Thread.currentThread();
			
			log("IN " + method.getName() + ", args = " + args);
			Object result = method.invoke(target, args);
			log("OUT " + method.getName() + ", result = " + result);
			
			return result;
		}
		
	} // ! InnerInvocationHandler
} // ! LoggingAspect
