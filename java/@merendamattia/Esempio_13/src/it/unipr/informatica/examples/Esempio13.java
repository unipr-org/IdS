package it.unipr.informatica.examples;

import java.util.ArrayList;
import java.util.List;

import it.unipr.informatica.aspects.PersistentAspect;
import it.unipr.informatica.aspects.interfaces.PersistentHandler;
import it.unipr.informatica.examples.model.Book;
import it.unipr.informatica.examples.model.simple.SimpleBook;

public class Esempio13 {
	private void printAndAddBooks(List<Book> books) {
		printBooks(books);
		
		for(int i = 0; i < 3; ++i) {
			int n = 10 + (int)(90 * Math.random());

			books.add(new SimpleBook(n, "Author #" + n, "Title #" + n));
		}		
	}
	
	private void printBooks(List <Book> books) {
		if (!books.isEmpty()) {
			System.out.println("Current books:");
		
			for(Book book : books)
				System.out.println(book);
		}
	}
	
	private void go() {
		try {
			PersistentHandler<ArrayList<Book>> bookHandler = PersistentAspect.attach("Books.dat", new ArrayList<Book>());
			
			List<Book> books = bookHandler.get();
		
			printAndAddBooks(books);
			
			bookHandler.commit();
			
			System.out.println("Books saved");
			
			printBooks(books);
			
			
		} catch(Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Esempio13().go();
	}
}
