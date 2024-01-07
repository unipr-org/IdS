package it.unipr.informatica.patterns.builder.abstractions;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface PersonBuilder {
	public PersonBuilder name(String name);

	public PersonBuilder surname(String surname);

	public PersonBuilder dateOfBirth(String date);

	public PersonBuilder faculty(String faculty);

	public PersonBuilder university(String university);

	public PersonBuilder registrationNumber(String registrationNumber);

	public PersonBuilder employment(String employment);

	public PersonBuilder teching(String teaching);
}
