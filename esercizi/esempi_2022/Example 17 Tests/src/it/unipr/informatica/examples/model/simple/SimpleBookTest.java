/*
 * SimpleBookTest
 *
 * (c) 2021 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.model.simple;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;

import it.unipr.informatica.examples.model.Book;

public class SimpleBookTest {
	@Test
	public void classImplementsCloneable() {
		Book book = new SimpleBook(1, "Author", "Title");
		
		assertTrue(book instanceof Cloneable);
	}

	@Test
	public void classImplementsSerializable() {
		Book book = new SimpleBook(1, "Author", "Title");
		
		assertTrue(book instanceof Serializable);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorIdLessThanOne() {
		new SimpleBook(0, "Author", "Title");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorAuthorNull() {
		new SimpleBook(1, null, "Title");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorAuthorEmpty() {
		new SimpleBook(1, "", "Title");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorTitleNull() {
		new SimpleBook(1, "Author", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorTitleEmpty() {
		new SimpleBook(1, "Author", "");
	}
	
	@Test
	public void getIDOK() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertEquals(1, book.getID());
	}

	@Test
	public void setIDOK() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setID(2);
		
		assertEquals(2, book.getID());
	}

	@Test
	public void getAuthorOK() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertEquals("Author", book.getAuthor());
	}

	@Test
	public void setAuthorOK() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setAuthor("Author new");
		
		assertEquals("Author new", book.getAuthor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAuthorNull() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setAuthor(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setAuthorEmpty() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setAuthor("");
	}

	@Test
	public void getTitleOK() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertEquals("Title", book.getTitle());
	}
	
	@Test
	public void setTitleOK() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setTitle("Title new");
		
		assertEquals("Title new", book.getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setTitleNull() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setTitle(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setTitleEmpty() {
		SimpleBook book = new SimpleBook(1, "Author", "Title");

		book.setTitle("");
	}
	
	@Test
	public void equalsOK() {
		Book book1 = new SimpleBook(1, "Author", "Title");

		Book book2 = new SimpleBook(1, "Author", "Title");

		assertEquals(book1, book2);
	}
	
	@Test
	public void equalsNotSimpleBook() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertNotEquals(book, new Object());
	}
	
	@Test
	public void equalsIDsDiffer() {
		Book book1 = new SimpleBook(1, "Author", "Title");

		Book book2 = new SimpleBook(2, "Author", "Title");

		assertNotEquals(book1, book2);
	}

	@Test
	public void equalsAuthorsDiffer() {
		Book book1 = new SimpleBook(1, "Author 1", "Title");

		Book book2 = new SimpleBook(1, "Author 2", "Title");

		assertNotEquals(book1, book2);
	}
	
	@Test
	public void equalsTitlesDiffer() {
		Book book1 = new SimpleBook(1, "Author", "Title 1");

		Book book2 = new SimpleBook(1, "Author", "Title 2");

		assertNotEquals(book1, book2);
	}
	
	@Test
	public void cloneOK() {
		Book book1 = new SimpleBook(1, "Author", "Title 1");

		Book book2 = book1.clone();

		assertEquals(book1, book2);
	}

	@Test
	public void toStringOK() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertEquals("ID=1, author=Author, title=Title", book.toString());
	}

	@Test
	public void hashCodeOK() {
		Book book = new SimpleBook(1, "Author", "Title");

		assertEquals(1 + "Author".hashCode() + "Title".hashCode(), book.hashCode());
	}
}
