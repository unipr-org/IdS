package it.unipr.informatica.exercise.esercizio8;

import it.unipr.informatica.exercise.esercizio8.model.*;

public class Esercizio8 {
	public static void main(String[] args) {
		L8Factory factory = L8FactoryImplementation.getInstance();
		Script s = factory.newScript(null); // TODO da terminare
		L8Interpreter.run(s);
	}
}
