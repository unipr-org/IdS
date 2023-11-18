package it.unipr.informatica.examples.model.simple;

import it.unipr.informatica.examples.model.Student;

public final class SimpleStudent implements Student, Cloneable {
	private int id;
	private String name;
	private String surname;

	// ------------------------------------------ Costruttori
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

	// ------------------------------------------ Getter
	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getSurname() {
		return surname;
	}

	// ------------------------------------------ Setter
	public void setID(int id) {
		if (id < 1)
			throw new IllegalArgumentException("id < 1");

		this.id = id;
	}

	public void setName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException("name == null || name.length() == 0");

		this.name = name;
	}

	public void setSurname(String surname) {
		if (surname == null || surname.length() == 0)
			throw new IllegalArgumentException("surname == null || surname.length() == 0");

		this.surname = surname;
	}

	// ------------------------------------------ Metodi
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
