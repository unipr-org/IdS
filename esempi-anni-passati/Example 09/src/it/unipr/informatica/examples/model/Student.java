/*
 * Student
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.model;

import it.unipr.informatica.beans.Bean;

public interface Student extends Bean {
	public int getID();

	public String getName();

	public String getSurname();
}
