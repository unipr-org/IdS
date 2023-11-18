package it.unipr.informatica.examples.model;

import it.unipr.informatica.beans.Bean;

public interface Student extends Bean {
	public int getID();

	public String getName();

	public String getSurname();
}