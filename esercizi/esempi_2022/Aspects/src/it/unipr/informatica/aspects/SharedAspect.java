/*
 * SharedAspect
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SharedAspect {
	public static <T> T attach(T target) {
		if (target == null)
			throw new IllegalArgumentException("target == null");

		Class<?> targetClass = target.getClass();

		Class<?>[] targetInterfaces = targetClass.getInterfaces();

		Object proxy = Proxy.newProxyInstance(targetClass.getClassLoader(), targetInterfaces,
				new InnerInvocationHandler(target));

		@SuppressWarnings("unchecked")
		T result = (T) proxy;

		return result;
	}

	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;

		private Object lock;

		private InnerInvocationHandler(Object target) {
			this.target = target;

			this.lock = new Object();
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
			try {
				synchronized (lock) {
					Object result = method.invoke(target, arguments);

					return result;
				}
			} catch (InvocationTargetException exception) {
				throw exception.getCause();
			} catch (Throwable throwable) {
				throw throwable;
			}
		}
	}
}
