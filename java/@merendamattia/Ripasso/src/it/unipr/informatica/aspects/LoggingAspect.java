package it.unipr.informatica.aspects;

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
		
		public InnerInvocationHandler(Object target){
			this.target = target;
			this.mutex = new Object();
			this.currentThread = null;
		}
		
		private void log(String msg) {			
			synchronized (mutex) {
				Timestamp now = Timestamp.from(Instant.now());
				System.out.println("[" + now + "\t" + currentThread.getName() + "] " + msg);
			}
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			currentThread = Thread.currentThread();		
			
			Object result = null;
			
			try {
				log("In\t" + method.getName());
				
				result = method.invoke(target, args);
				
				log("Out\t" + method.getName() + ", result=" + result);
			
			} catch (Exception e) {
				
				log("Out\t" + method.getName() + ", exception=" + e.getCause());
				throw e;
			}
			
			return result;
		}
		
	} // ! InnerInvocationHandler
} // ! LoggingAspect
