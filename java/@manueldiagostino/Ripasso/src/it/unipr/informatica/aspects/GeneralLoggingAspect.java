package it.unipr.informatica.aspects;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class GeneralLoggingAspect {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss.SSS");
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(T object) {
		if (object == null)
			throw new IllegalArgumentException("object == null");
		
		Class<?> clazz = object.getClass();
		
		/**
		 * public PrintStream(OutputStream out,
               boolean autoFlush,
               String encoding)
		 */
		PrintStream stream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InnerLogger<T>(object, stream));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(T object, OutputStream outStream) {
		if (object == null)
			throw new IllegalArgumentException("object == null");
		if (outStream == null)
			throw new IllegalArgumentException("outStream == null");
		
		Class<?> clazz = object.getClass();
		
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InnerLogger<T>(object, outStream));
	}
	
	public static <T> T attach(T object, String filename) throws IOException {
		return attach(object, new File(filename));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T attach(T object, File outFile) throws IOException {
		if (object == null)
			throw new IllegalArgumentException("object == null");
		if (outFile == null)
			throw new IllegalArgumentException("outFile == null");
		if (outFile.isDirectory())
			throw new IllegalArgumentException("outFile is a directory");
		
		FileOutputStream fileOutStream = null;
		BufferedOutputStream bufferedStream = null;
		PrintStream stream = null;
		
		try {
			fileOutStream = new FileOutputStream(outFile.getAbsolutePath());
			bufferedStream = new BufferedOutputStream(fileOutStream);
			stream = new PrintStream(bufferedStream, true, StandardCharsets.UTF_8);
		} catch (Exception e) {
			if (fileOutStream != null)
				fileOutStream.close();
			if (bufferedStream != null)
				bufferedStream.close();
			if (stream != null)
				fileOutStream.close();
			
			throw e;
		}
		Class<?> clazz = object.getClass();
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InnerLogger<T>(object, stream));
	}	
	
	private static class InnerLogger<T> implements InvocationHandler {
		private T target = null;
		private PrintStream outStream = null;
		
		private InnerLogger(T target, OutputStream outStream) {
			this(target, new PrintStream(outStream, true, StandardCharsets.UTF_8));
		}
		
		private InnerLogger(T target, PrintStream outStream) {
			this.target = target;
			this.outStream = outStream;
		}
		

		private void log(String message) {
			Thread t = Thread.currentThread();
			String threadName = t.getName();
			String targetClass = target.getClass().getCanonicalName();
			String currentTime = dateFormat.format(System.currentTimeMillis());
			
			synchronized (outStream) {
				outStream.printf("[%s %s %s] %s\n", currentTime, threadName, targetClass, message);
				outStream.flush();
			}
			
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			Object result = null;
			
			log("In " + method.getName() + " " + Arrays.toString(args));
			try {
				result = method.invoke(target, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				log(method.getName() + " with args " + Arrays.toString(args) + " throwed " + e.getMessage());
				outStream.close();
				throw e;
			}
			log("Out " + method.getName() + " returned " + result);
			
			return result;
		}
		
	}
	
	private void testStdOut() {
		Deque<Integer> list = GeneralLoggingAspect.attach(new LinkedList<Integer>());
		list.add(1);		
		list.add(2);		
		list.add(3);		
		list.add(4);	
		list.removeFirst();
		list.removeLast();
		list.removeLast();
		list.removeLast();
		list.removeLast(); // throws an exception
	}
	
	private void testFile(String filename) throws IOException {
		Deque<Integer> list = GeneralLoggingAspect.attach(new LinkedList<Integer>(), new File(filename));
		list.add(1);		
		list.add(2);		
		list.add(3);		
		list.add(4);	
		list.removeFirst();
		list.removeLast();
		list.removeLast();
		list.removeLast();
	}
	
	private void testStringFilename(String filename) throws IOException {
		Deque<Integer> list = GeneralLoggingAspect.attach(new LinkedList<Integer>(), filename);
		list.add(5);		
		list.add(6);		
		list.add(7);		
		list.add(8);	
		list.removeFirst();
		list.removeLast();
		list.removeLast();
		list.removeLast();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		GeneralLoggingAspect aspect = new GeneralLoggingAspect();
		String filename = "./src/it/unipr/informatica/aspects/log";
		
		try {
			aspect.testStdOut();
		} catch (Exception e) {
			
		}
		
		aspect.testFile(filename+"1.txt");
		aspect.testStringFilename(filename+"2.txt");
	}
}
