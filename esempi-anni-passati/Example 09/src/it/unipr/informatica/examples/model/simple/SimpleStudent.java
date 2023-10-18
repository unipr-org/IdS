/*
 * SimpleStudent
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.model.simple;

import it.unipr.informatica.examples.model.Student;

public class SimpleStudent implements Student, Cloneable {
	private int id;

	private String name;

	private String surname;

	public SimpleStudent() {
		this.id = 0;

		this.name = this.surname = "";
	}

	public SimpleStudent(int id, String name, String surname) {
		if (id < 1)
			throw new IllegalArgumentException("id < 1");

		if (surname == null || surname.length() == 0)
			throw new IllegalArgumentException("surname == null || surname.length() == 0");

		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("name == null || name.length() == 0");

		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public int getID() {
		return id;
	}

	public void setID(int id) {
		if (id < 1)
			throw new IllegalArgumentException("id < 1");

		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("name == null || name.length() == 0");

		this.name = name;
	}

	@Override
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname == null || surname.length() == 0)
			throw new IllegalArgumentException("surname == null || surname.length() == 0");

		this.surname = surname;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof SimpleStudent))
			return false;

		SimpleStudent otherStudent = (SimpleStudent) other;

		return id == otherStudent.id && name.equals(otherStudent.name) && surname.equals(otherStudent.surname);
	}

	@Override
	protected SimpleStudent clone() throws CloneNotSupportedException {
		return new SimpleStudent(id, name, surname);
	}

	@Override
	public int hashCode() {
		return id + name.hashCode() + surname.hashCode();
	}

	@Override
	public String toString() {
		return "ID=" + id + ", surname=" + surname + ", name=" + name;
	}
}
