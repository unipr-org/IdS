/*
 * LoggingAspect
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class LoggingAspect {
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,SSS");

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

	private static void log(String message) {
		String now = DATE_FORMAT.format(System.currentTimeMillis());

		System.out.printf("[%s %s] %s\n", Thread.currentThread().getName(), now, message);
	}

	private static class InnerInvocationHandler implements InvocationHandler {
		private Object target;

		private InnerInvocationHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
			String name = method.getName();

			try {
				log("In  " + name + " " + Arrays.toString(arguments));

				Object result = method.invoke(target, arguments);

				log("Out " + name + " " + result);

				return result;
			} catch (InvocationTargetException exception) {
				Throwable cause = exception.getCause();

				log("Out " + name + " " + cause.getMessage());

				throw cause;
			} catch (Throwable throwable) {
				log("Out " + name + " " + throwable.getMessage());

				throw throwable;
			}
		}
	}
}
