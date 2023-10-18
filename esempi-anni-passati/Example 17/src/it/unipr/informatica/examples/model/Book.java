/*
 * Book
 *
 * (c) 2021 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.model;

import java.io.Serializable;

public interface Book extends Cloneable, Serializable {
	public int getID();
	
	public String getAuthor();
	
	public String getTitle();
	
	public Book clone();
}
