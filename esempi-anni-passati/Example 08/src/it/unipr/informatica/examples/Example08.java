/*
 * Example08
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.Scanner;

public class Example08 {
	private void dump(Class<?> clazz) {
		System.out.println("Class: " + clazz.getName());

		Class<?> baseClass = clazz.getSuperclass();

		if (baseClass != null)
			System.out.println("Base class: " + baseClass.getName());

		Class<?>[] interfaces = clazz.getInterfaces();

		for (int i = 0; i < interfaces.length; ++i)
			System.out.println("Implemented interface: " + interfaces[i].getName());

		Field[] fields = clazz.getFields();

		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];

			Class<?> fieldClass = field.getType();

			System.out.println("Field: " + fieldClass.getName() + " " + field.getName());
		}

		Constructor<?>[] constructors = clazz.getConstructors();

		for (int i = 0; i < constructors.length; ++i) {
			Constructor<?> constructor = constructors[i];

			System.out.print("Constructor: " + constructor.getName() + "(");

			Parameter[] parameters = constructor.getParameters();

			for (int j = 0; j < parameters.length; ++j) {
				Parameter parameter = parameters[j];

				Class<?> parameterClass = parameter.getType();

				System.out.print(parameterClass.getName() + " " + parameter.getName());

				if (j != parameters.length - 1)
					System.out.print(", ");
			}

			System.out.println(")");
		}

		Method[] methods = clazz.getMethods();

		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];

			Class<?> resultType = method.getReturnType();

			System.out.print("Method: " + resultType.getName() + " " + method.getName() + "(");

			Parameter[] parameters = method.getParameters();

			for (int j = 0; j < parameters.length; ++j) {
				Parameter parameter = parameters[j];

				Class<?> parameterClass = parameter.getType();

				System.out.print(parameterClass.getName() + " " + parameter.getName());

				if (j != parameters.length - 1)
					System.out.print(", ");
			}

			System.out.println(")");
		}
	}

	private void show(String className) {
		try {
			Class<?> clazz = Class.forName(className);

			dump(clazz);
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Cannot load " + className);
		}
	}

	private void go() {
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.useLocale(Locale.US);
			
			System.out.print("Please, enter the fully qualified name of a class: ");

			String className = scanner.nextLine();

			show(className);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example08().go();
	}
}
