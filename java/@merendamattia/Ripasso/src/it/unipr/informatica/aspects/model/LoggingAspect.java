package it.unipr.informatica.aspects.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

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
	
	private static void log(String msg) {			
		Timestamp now = Timestamp.from(Instant.now());
		synchronized (LoggingAspect.class) {
			System.out.printf("[%s\t%s] %s\n", now, Thread.currentThread().getName(), msg);
		}
	}
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;
		
		public InnerInvocationHandler(Object target){
			this.target = target;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {			
			Object result = null;
			
			try {
				log("In\t" + method.getName() + ", args=" + Arrays.toString(args));
				
				result = method.invoke(target, args);
				
				log("Out\t" + method.getName() + ", result=" + result);
			
			} catch (Exception e) {
				
				log("Exc\t" + method.getName() + ", exception=" + e.getCause());
				throw e;
			}
			
			return result;
		}
		
	} // ! InnerInvocationHandler
} // ! LoggingAspect
