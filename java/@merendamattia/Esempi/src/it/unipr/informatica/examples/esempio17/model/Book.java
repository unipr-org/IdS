package it.unipr.informatica.examples.esempio17.model;

import java.io.Serializable;

public interface Book extends Cloneable, Serializable {
	public int getID();
	
	public String getAuthor();
	
	public String getTitle();
	
	public Book clone();
}