package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SharedAspect {
	@SuppressWarnings("unchecked")
	public static <T> T attach(T target) {
		if(target == null)
			throw new NullPointerException("target == null");
		
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		
		return (T) proxy;
	}
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;
		private Object mutex;
		
		public InnerInvocationHandler(Object target) {
			this.target = target;
			this.mutex = new Object();
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			try {
				synchronized (mutex) {
					Object result = method.invoke(target, args);
					return result;
				}	
				
			} catch (Throwable e) {
				throw e;
			}
		}
		
	} // ! InnerInvocationHandler
} // ! SharedAspect
