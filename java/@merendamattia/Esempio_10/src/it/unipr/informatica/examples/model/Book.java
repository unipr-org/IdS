package it.unipr.informatica.examples.model;

import it.unipr.informatica.beans.Bean;

public interface Book extends Bean {
	public int getID();

	public String getAuthor();

	public String getTitle();
}