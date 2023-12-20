package it.unipr.informatica.patterns.builder.implementations;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Student {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String faculty;
	private String university;
	private String registrationNumber;
	
	public Student(
			String name,
			String surname,
			String dateOfBirth,
			String faculty,
			String university,
			String registrationNumber) {
		
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.faculty = faculty;
		this.university = university;
		this.registrationNumber = registrationNumber;		
	}
	
	public void printInfo() {
		System.out.println("Name: " + name);
		System.out.println("Surname: " + surname);
		System.out.println("Date of birth: " + dateOfBirth);
		System.out.println("Faculty: " + faculty);
		System.out.println("University: " + university);
		System.out.println("Registration number: " + registrationNumber);
	}
}
