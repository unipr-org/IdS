package it.unipr.informatica.patterns.builder;

import it.unipr.informatica.patterns.builder.abstractions.PersonBuilder;
import it.unipr.informatica.patterns.builder.implementations.Student;
import it.unipr.informatica.patterns.builder.implementations.StudentBuilder;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Client {
	public static void main(String[] args) {
		StudentBuilder builder = StudentBuilder.newStudentBuilder();
		PeopleDirector.performBuild(PepleType.STUDENT, builder);
		Student studentDefault = builder.build();
		studentDefault.printInfo();

		builder = StudentBuilder.newStudentBuilder();
		builder.name("Paolo").surname("Rossi").dateOfBirth("13-09-2000").university("UniVr").faculty("Med")
				.registrationNumber("000001");
		builder.build().printInfo();
	}
}
