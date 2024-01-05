package it.unipr.informatica.exercise.esercizio8;

import it.unipr.informatica.exercise.esercizio8.model.*;

public class L8Interpreter {
	public static void run(Script script) {
		if(script == null)
			throw new IllegalArgumentException("script == null");
		
		Visitor v = new L8Visitor();
		script.accept(v);
	}
}
