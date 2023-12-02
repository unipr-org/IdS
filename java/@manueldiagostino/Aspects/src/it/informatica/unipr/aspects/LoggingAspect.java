package it.informatica.unipr.aspects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class LoggingAspect {
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY_MM-dd HH:mm:ss,SSS");
	private static PrintStream outputStream_;
	
	public static <T> T attach(T target) {
		return LoggingAspect.attach(target, System.out);
	}
	
	public static <T> T attach(T target, String file) throws FileNotFoundException {
		return LoggingAspect.attach(target, new PrintStream(file));
	}
	
	public static <T> T attach(T target, PrintStream outputStream) {
		if (target == null)
			throw new IllegalArgumentException("<"+LoggingAspect.class.getName()+">" + "target == null");
		
		outputStream_ = outputStream;
		Class<?> targetClassDescriptor = target.getClass();
		
		@SuppressWarnings("unchecked")
		T result = (T) Proxy.newProxyInstance(
				targetClassDescriptor.getClassLoader(),
				targetClassDescriptor.getInterfaces(),
				new InnerInvocationHandler(target));
		
		return result;
	}
	
	
	private static class InnerInvocationHandler implements InvocationHandler {
		private final Object target_;
		
		public InnerInvocationHandler(Object target) {
			target_ = target;
		}

		private void log(String message) {
			String now = DATE_FORMAT.format(System.currentTimeMillis());
			
			synchronized (DATE_FORMAT) {
				outputStream_.printf("[%s %s %s] %s\n", now, Thread.currentThread().getName(), target_.getClass().getName(), message);
			}
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String methodName = method.getName();
			
			try {
				log("In " + methodName + " " + Arrays.toString(args));
				Object result = method.invoke(target_, args);
				log("In " + methodName + " " + result);
				
				return result;
			} catch (InvocationTargetException exception) {
				log("In " + exception.getCause().getMessage());
				throw exception;
			} catch (Throwable throwable) {
				log("In " + throwable.getMessage());
				throw throwable;
			}			
		}		
	}
}
