package it.unipr.informatica.exercises.esercizio1_4_1.commands;

import java.io.File;

import it.unipr.informatica.exercises.esercizio1_4_1.Command;

public class CreateFile implements Command {
	private String name;
	
	public CreateFile(String name) {
		this.name = name;
	}
	
	@Override
	public Object execute() {
		return new File(name);
	}

} // ! CreateFile
