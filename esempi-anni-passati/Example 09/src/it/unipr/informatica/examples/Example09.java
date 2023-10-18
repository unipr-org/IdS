/*
 * Example09
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.util.List;

import it.unipr.informatica.beans.Bean;
import it.unipr.informatica.beans.BeanLoader;
import it.unipr.informatica.examples.model.simple.SimpleBook;
import it.unipr.informatica.examples.model.simple.SimpleStudent;

public class Example09 {
	private void go() {
		try {
			BeanLoader loader = new BeanLoader();

			List<SimpleStudent> studentBeans = loader.load(SimpleStudent.class, "Students.csv");

			for (Bean bean : studentBeans)
				System.out.println(bean);

			System.out.println();

			List<SimpleBook> bookBeans = loader.load(SimpleBook.class, "Books.csv");

			for (Bean bean : bookBeans)
				System.out.println(bean);
		} catch (Throwable throwable) {
			System.err.println("Cannot load beans with message " + throwable.getMessage());
		}
	}

	public static void main(String[] args) {
		new Example09().go();
	}
}
