package it.unipr.informatica.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BeanLoader {
	
	/*
	 * Spiegazione presente in Esempio_09
	 */
	
	public <T extends Bean> List<T> load(Class<T> clazz, String fileName) throws IOException {
		// Faccio i controlli necessari prima di eseguire il tutto
		if(clazz == null || !clazz.isInterface())
			throw new IllegalArgumentException("clazz == null || !clazz.isInterface()");
		if(fileName == null || fileName.length() == 0)
			throw new IllegalArgumentException("fileName == null || fileName.length() == 0");
		
		try (
				InputStream inputStream = new FileInputStream(fileName); 
				Scanner scanner = new Scanner(inputStream)
						) {
			scanner.useLocale(Locale.US);

			String heading = scanner.nextLine();
			String[] propertyNames = split(heading);
			
			// Viene ottenuto un array di tipi di proprietà (propertyTypes) in base ai nomi delle proprietà.
			Class<?>[] propertyTypes = getPropertyTypes(clazz, propertyNames);

			List<T> result = new ArrayList<T>();

			while (scanner.hasNext()) {
				@SuppressWarnings("unchecked")
				T bean = (T) Proxy.newProxyInstance(
						clazz.getClassLoader(), 
						new Class<?>[] { clazz }, 
						new InvocationHandler() {

							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								String message = method.getName();
								
								if("getID".equals(message))
									return 1;
								if("getSurname".equals(message))
									return "Rossi";
								if("getName".equals(message))
									return "Mario";
								
								throw new IllegalArgumentException("Illegal message");
							}
							
						}
					);

				String line = scanner.nextLine();

				String[] values = split(line);

				if (propertyNames.length != values.length)
					throw new IOException("invalid file format");

				result.add(bean);
			}

			return result;
		
		} catch (IOException exception) {
			throw exception;
		} catch (Throwable throwable) {
			throw new IOException(throwable);
		}
	}

	/*
	 * text: la stringa che deve essere convertita.
	 * clazz: la classe che rappresenta il tipo di dati in cui la stringa deve essere convertita.
	 */
	protected Object fromString(String text, Class<?> clazz) {
		if (clazz == String.class)
			return text;

		if (clazz == Integer.TYPE)
			return Integer.parseInt(text);

		if (clazz == Float.TYPE)
			return Float.parseFloat(text);

		if (clazz == Double.TYPE)
			return Double.parseDouble(text);

		throw new IllegalArgumentException("cannot convert " + text + " to " + clazz.getName());
	}
	
	
	private <T> Class<?>[] getPropertyTypes(Class<T> clazz, String[] propertyNames) throws IOException {	
		Class<?>[] propertyTypes = new Class<?>[propertyNames.length];
		
		for (int i = 0; i < propertyNames.length; ++i) {
			String propertyName = propertyNames[i];
			String methodName = "set" + propertyName;
			
			try {
				Method method = clazz.getMethod(methodName);
				propertyTypes[i] = method.getReturnType();
			} catch (NoSuchMethodException e) {
				throw new IOException("invalid property name " + propertyName);
			}	
		}

		return propertyTypes;
	}

	private String[] split(String line) {
		String[] values = line.split("\t");

		for (int i = 0; i < values.length; ++i)
			values[i] = values[i].trim();

		return values;
	}
}
