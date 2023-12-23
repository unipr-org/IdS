package it.unipr.informatica.exercises.esercizio1_4_1.commands;

import java.io.File;

import it.unipr.informatica.exercises.esercizio1_4_1.Command;

public class DeleteFile implements Command {
	private File file;
	
	public DeleteFile(File file) {
		this.file = file;
	}
	
	/**
	 * @return true if and only if the file or directory is successfully deleted; false otherwise
	 */
	@Override
	public Object execute() {
		return file.delete();
	}

} // ! DeleteFile
