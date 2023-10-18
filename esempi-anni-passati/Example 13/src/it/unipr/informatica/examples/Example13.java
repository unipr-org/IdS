/*
 * Example13
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.util.ArrayList;
import java.util.List;

import it.unipr.informatica.aspects.PersistentAspect;
import it.unipr.informatica.aspects.PersistentHandler;
import it.unipr.informatica.examples.model.Book;
import it.unipr.informatica.examples.model.simple.SimpleBook;

public class Example13 {
	private void printAndAddBooks(List<Book> books) {
		if (!books.isEmpty()) {
			System.out.println("Current books:");
		
			for(Book book : books)
				System.out.println(book);
		}
		
		for(int i = 0; i < 3; ++i) {
			int n = 10 + (int)(90 * Math.random());

			books.add(new SimpleBook(n, "Author #" + n, "Title #" + n));
		}		
	}
	
	private void go() {
		try {
			PersistentHandler<ArrayList<Book>> bookHandler = PersistentAspect.attach("Books.dat", new ArrayList<Book>());
			
			List<Book> books = bookHandler.get();
		
			printAndAddBooks(books);
			
			bookHandler.commit();
			
			System.out.println("Books saved");
		} catch(Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example13().go();
	}
}
