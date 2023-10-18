/*
 * Example10
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import it.unipr.informatica.examples.model.Student;

public class Example10 {
	private Object handler(Object proxy, Method method, Object[] arguments) throws Throwable {
		String methodName = method.getName();

		switch (methodName) {
		case "getID":
			return 1;
		case "getSurname":
			return "Verdi";
		case "getName":
			return "Giuseppe";
		default:
			throw new IllegalArgumentException("unsupported " + method.getName());
		}
	}

	private void go() {
		Student student = (Student) Proxy.newProxyInstance(getClass().getClassLoader(),
				new Class<?>[] { Student.class }, this::handler);

		System.out.println("ID: " + student.getID());
		System.out.println("Name: " + student.getName());
		System.out.println("Surname: " + student.getSurname());

		System.out.println();
	}

	public static void main(String[] args) {
		new Example10().go();
	}
}
