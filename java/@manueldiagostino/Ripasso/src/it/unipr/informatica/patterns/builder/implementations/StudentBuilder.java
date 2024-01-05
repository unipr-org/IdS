package it.unipr.informatica.patterns.builder.implementations;

import it.unipr.informatica.patterns.builder.abstractions.PersonBuilder;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class StudentBuilder implements PersonBuilder {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String faculty;
	private String university;
	private String employment;
	private String teaching;
	private String registrationNumber;

	private StudentBuilder() {
		// Blank
	}

	public static StudentBuilder newStudentBuilder() {
		return new StudentBuilder();
	}

	@Override
	public PersonBuilder name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public PersonBuilder surname(String surname) {
		this.surname = surname;
		return this;
	}

	@Override
	public PersonBuilder dateOfBirth(String date) {
		this.dateOfBirth = date;
		return this;
	}

	@Override
	public PersonBuilder faculty(String faculty) {
		this.faculty = faculty;
		return this;
	}

	@Override
	public PersonBuilder university(String university) {
		this.university = university;
		return this;
	}

	@Override
	public PersonBuilder employment(String employment) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public PersonBuilder registrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
		return this;
	}

	@Override
	public PersonBuilder teching(String teaching) {
		this.teaching = teaching;
		return this;
	}

	public Student build() {
		return new Student(name, surname, dateOfBirth, faculty, university, registrationNumber);
	}

}
