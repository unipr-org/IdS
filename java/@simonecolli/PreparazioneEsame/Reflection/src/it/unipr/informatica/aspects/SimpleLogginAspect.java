package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.time.Instant;


public class SimpleLogginAspect {
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(T target) {
		
		if(target == null)
			throw new IllegalArgumentException("target == null");
		
		Class<?> clazz = target.getClass();
		
		Class<?>[] interfaces = clazz.getInterfaces();
		
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		
		
		return (T) proxy;
	}
	
	
	private static class InnerInvocationHandler<T> implements InvocationHandler {
		
		private T target_;
		private Object mutex_;
		
		public InnerInvocationHandler(T target) {
			target_ = target;
			mutex_ = new Object();
		}
		
		private void log(String msg) {
			Thread currentThread = Thread.currentThread();
			synchronized (mutex_) {
				Timestamp now = Timestamp.from(Instant.now());
				System.out.printf("[%s], %s, %s, %s\n",
						now,
						currentThread.getName(),
						currentThread.getClass(),
						msg
					);
			}
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			Object res = null;
			
			try {
				log("In "  + method.getName());
				res = method.invoke(target_, args);
				log("Out " + method.getName() + " " + res);
				
			}catch (Throwable t) {
				log("Out " + method.getName() + " " + t.toString());
				throw t;
			}
			
			return res;
		}
		
	}
	
}
