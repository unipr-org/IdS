package it.unipr.informatica.exercises.esercizio1_4_1.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import it.unipr.informatica.exercises.esercizio1_4_1.Command;

public class ReadFile implements Command {
	private File file;
	
	public ReadFile(File file) {
		this.file = file;
	}
	
	/**
	 * @return String context with the file's context, null otherwise
	 */
	@Override
	public Object execute() {
		if(file.canRead()) {
			
			String context = "";
			
			try (Scanner sc = new Scanner(file)) {
		
				while(sc.hasNextLine()) {
					context += sc.nextLine();
				}
		
			} catch (FileNotFoundException e) {
				System.out.println(e.getCause());
			}
			
			return context;
			
		}
		
		return null;
	}

} // ! ReadFile
