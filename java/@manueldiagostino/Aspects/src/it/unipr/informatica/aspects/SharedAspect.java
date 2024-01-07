package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class SharedAspect {
	public static <T> T attach(T target) {
		if (target == null)
			throw new IllegalArgumentException("<"+SharedAspect.class.getName()+">" + "target == null");
		
		Class<?> targetClassDescriptor = target.getClass();
		
		@SuppressWarnings("unchecked")
		T result = (T) Proxy.newProxyInstance(targetClassDescriptor.getClassLoader(), targetClassDescriptor.getInterfaces(), new InnerInvocationHandler<>(target));
		return result;
	}
	
	private static class InnerInvocationHandler<T> implements InvocationHandler {
		private final Object mutex_;
		private final T target_;
		
		private InnerInvocationHandler(T target) {
			mutex_ = new Object();
			target_ = target;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			synchronized (mutex_) {
				try {
					/**
					 * Manda un messaggio (invocazione del metodo) che ha come mittente `target_`
					 * e come argomenti `args`; il destinatario è difatti `method`.
					 */
					System.out.println("<"+target_.getClass().getName()+">" + " invoking " + method.getName());
					return method.invoke(target_, args);
				} catch (InvocationTargetException e) {
					return e.getCause();
				} catch (Exception e) {
					return e;
				}
			}
		}
		
	}
}
