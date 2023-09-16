/*
 * SimpleBook
 *
 * (c) 2021 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.model.simple;

import it.unipr.informatica.examples.model.Book;

public final class SimpleBook implements Book {
	private static final long serialVersionUID = -944174200930040774L;

	private int ID;

	private String author;

	private String title;

	public SimpleBook(int ID, String author, String title) {
		if (ID < 1)
			throw new IllegalArgumentException("ID < 1");

		if (author == null || author.length() == 0)
			throw new IllegalArgumentException("author == null || author.length() == 0");

		if (title == null || title.length() == 0)
			throw new IllegalArgumentException("title == null || title.length() == 0");
		
		this.ID = ID;
		
		this.author = author;

		this.title = title;
	}

	@Override
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if (author == null || author.length() == 0)
			throw new IllegalArgumentException("author == null || author.length() == 0");

		this.author = author;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.length() == 0)
			throw new IllegalArgumentException("title == null || title.length() == 0");

		this.title = title;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof SimpleBook))
			return false;
		
		SimpleBook otherBook = (SimpleBook)other;

		return ID == otherBook.ID && title.equals(otherBook.title) && author.equals(otherBook.author);
	}
	
	@Override
	public SimpleBook clone() {
		return new SimpleBook(ID, author, title);
	}
	
	@Override
	public int hashCode() {
		return ID + title.hashCode() + author.hashCode();
	}
	
	@Override
	public String toString() {
		return "ID=" + ID + ", author=" + author + ", title=" + title;
	}
}
