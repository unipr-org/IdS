package it.unipr.informatica.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class SimpleLoggingAspect {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YY-MM-dd HH:mm:ss.SSS");
	
	public static <T> T attach(T target) {
		if (target == null)
			throw new IllegalArgumentException("target == null");

		Class<?> clazz = target.getClass();

		@SuppressWarnings("unchecked")
		T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
				new InnerInvocationHandler<T>(target));

		return result;
	}

	private static class InnerInvocationHandler<T> implements InvocationHandler {
		private T target_;
		Object mutex_;

		public InnerInvocationHandler(T target) {
			target_ = target;
			mutex_ = new Object();
		}

		private void log(String message) {
			Thread current = Thread.currentThread();
			String currentThreadName = current.getName();
			String currentThreadClass = target_.getClass().getCanonicalName();
			
			String currentTime = dateFormat.format(System.currentTimeMillis());

			synchronized (mutex_) {
//				Timestamp now = Timestamp.from(Instant.now());
//				System.out.printf("[%s %s %s] %s\n", now, currentThreadName, currentThreadClass, message);
				System.out.printf("[%s %s %s] %s\n", currentTime, currentThreadName, currentThreadClass, message);
			}
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			try {
				log("In " + method.getName() + " " + Arrays.toString(args));
				result = method.invoke(target_, args);
				log("Out " + method.getName() + " [" + result + "]");
			} catch (Exception e) {
				log("Exception " + e.getCause());
				throw e;
			}

			return result;
		}

	}
}
