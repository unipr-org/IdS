/*
 * BeanLoader
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.beans;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BeanLoader {
	public <T extends Bean> List<T> load(Class<T> clazz, String fileName) throws IOException {
		try (InputStream inputStream = new FileInputStream(fileName); Scanner scanner = new Scanner(inputStream)) {
			scanner.useLocale(Locale.US);

			String heading = scanner.nextLine();

			String[] propertyNames = split(heading);

			Class<?>[] propertyTypes = getPropertyTypes(clazz, propertyNames);

			List<T> result = new ArrayList<T>();

			while (scanner.hasNext()) {
				T bean = (T) clazz.newInstance();

				String line = scanner.nextLine();

				String[] values = split(line);

				if (propertyNames.length != values.length)
					throw new IOException("invalid file format");

				for (int i = 0; i < propertyNames.length; ++i) {
					Method method = clazz.getMethod("set" + propertyNames[i], propertyTypes[i]);

					Object value = fromString(values[i], propertyTypes[i]);

					method.invoke(bean, value);
				}

				result.add(bean);
			}

			return result;
		} catch (IOException exception) {
			throw exception;
		} catch (Throwable throwable) {
			throw new IOException(throwable);
		}
	}

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
		Method[] methods = clazz.getMethods();

		Class<?>[] propertyTypes = new Class<?>[propertyNames.length];

		for (int i = 0; i < propertyNames.length; ++i) {
			String propertyName = propertyNames[i];

			String methodName = "set" + propertyName;

			for (int j = 0; j < methods.length; ++j) {
				Method method = methods[j];

				if (method.getParameterCount() == 1 && method.getReturnType() == Void.TYPE
						&& methodName.equals(method.getName())) {
					Class<?>[] parameterTypes = method.getParameterTypes();

					propertyTypes[i] = parameterTypes[0];

					break;
				}
			}

			if (propertyTypes[i] == null)
				throw new IOException("invalid property name " + propertyName);
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
