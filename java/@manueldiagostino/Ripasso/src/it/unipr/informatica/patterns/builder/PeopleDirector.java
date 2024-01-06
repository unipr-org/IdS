package it.unipr.informatica.patterns.builder;

import it.unipr.informatica.patterns.builder.abstractions.PersonBuilder;
import it.unipr.informatica.patterns.builder.implementations.StudentBuilder;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
enum PepleType {
	STUDENT, PROFESSOR
}

public class PeopleDirector {
	public volatile static PeopleDirector instance;

	private PeopleDirector() {
	}

	public static PeopleDirector getPeopleDirector() {
		if (instance == null)
			synchronized (PeopleDirector.class) {
				if (instance == null)
					instance = new PeopleDirector();
			}

		return instance;
	}

	public static PersonBuilder performBuild(PepleType type, PersonBuilder builder) {
		switch (type) {
		case STUDENT: {
			builder.name("Mario").surname("Rossi").dateOfBirth("12-12-1970").faculty("Computer Science")
					.university("UniPr").registrationNumber("000000");
			break;
		}
		case PROFESSOR: {
			builder.name("Mario").surname("Rossi").dateOfBirth("12-12-1970").faculty("Computer Science")
					.university("UniPr").employment("Professor").teching("Algorithms").registrationNumber("000000");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}

		return builder;
	}
}
