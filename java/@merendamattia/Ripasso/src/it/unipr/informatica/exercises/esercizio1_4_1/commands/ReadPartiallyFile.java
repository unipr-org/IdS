package it.unipr.informatica.exercises.esercizio1_4_1.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import it.unipr.informatica.exercises.esercizio1_4_1.Command;

public class ReadPartiallyFile implements Command {
	private File file;
	private int begin;
	private int end;
	
	public ReadPartiallyFile(File file, int begin, int end) {
		this.file = file;
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * @return String {@code context} with the file's context from row {@code begin} to row {@code end}, null otherwise
	 */
	@Override
	public Object execute() {
		int c = 0;
		
		if(file.canRead()) {
			
			String context = "";
			
			try (Scanner sc = new Scanner(file)) {
		
				while(sc.hasNextLine()) {
					// System.out.println(sc.nextLine() + " " + c);
					if(c >= begin && c < end)
						context += (sc.nextLine() + "\n");
					
					c++;
				}
		
			} catch (FileNotFoundException e) {
				System.out.println(e.getCause());
			}
			
			return context;
			
		}
		
		return null;
	}

} // ! ReadPartiallyFile
