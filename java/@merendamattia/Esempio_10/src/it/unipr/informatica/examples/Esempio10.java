package it.unipr.informatica.examples;

import java.util.List;

import it.unipr.informatica.beans.Bean;
import it.unipr.informatica.beans.BeanLoader;
import it.unipr.informatica.examples.model.Book;
import it.unipr.informatica.examples.model.Student;

public class Esempio10 {
	private void go() {
		try {
			BeanLoader loader = new BeanLoader();

			List<Student> studentBeans = loader.load(Student.class, "Students.csv");

			for (Student student : studentBeans)
				System.out.println(student.getSurname());

			System.out.println();

			List<Book> bookBeans = loader.load(Book.class, "Books.csv");

			for (Bean bean : bookBeans)
				System.out.println(bean); // Qui e' nascosto toString()
		} catch (Throwable throwable) {
			System.err.println("Cannot load beans with message " + throwable.getMessage());
		}
	}

	/*
	 * L'obiettivo è utilizzare la riflessione per creare istanze di oggetti Bean basate su un file di input specifico.
	 */
	public static void main(String[] args) {
		new Esempio10().go();
	}
}