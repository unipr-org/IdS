package it.unipr.informatica.exercises.esercizio1_4_1.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.unipr.informatica.exercises.esercizio1_4_1.Command;

public class WriteFile implements Command {
	private File file;
	private String context;
	
	public WriteFile(File file, String context) {
		this.file = file;
		this.context = context;
	}
	
	/**
	 * @return true if and only if the file is successfully written; false otherwise
	 */
	@Override
	public Object execute() {
		if(file.canWrite()) {
			try (FileWriter fw = new FileWriter(file)) {
				
				fw.write(context);
				
				return true;
			} catch (IOException e) {
				System.out.println(e.getCause());
			}
		}
		return false;
	}

} // ! WriteFile
