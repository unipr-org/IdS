package it.unipr.informatica.exercises.esercizio1_4_1;

import java.io.File;
import java.io.IOException;

import it.unipr.informatica.exercises.esercizio1_4_1.commands.*;

public class EditableFileImpl implements EditableFile {
	private File file;
	private String dir;
	private String name;
	
	public EditableFileImpl(String dir) {
		this.file = null;
		this.dir = dir;
		this.name = null;
	}
	
	@Override
	public void create(String name) {	
		this.name = name;
		
		if(name == null)
			throw new IllegalArgumentException("name == null");

		try {
			file = (File) new CreateFile(dir + name).execute();
			
			if (file.createNewFile()) 
				System.out.println("File created: " + name);
			else 
				System.out.println("File " + name + " already exists.");
			
		} catch (IOException e) {
			System.err.println("Impossibile creare file " + name + " " + e.getCause());
		}
	}

	@Override
	public void delete() {
		boolean result = (boolean) new DeleteFile(file).execute();
		
		if (result) 
			System.out.println("Deleted the file: " + name);
		else 
		    System.out.println("Failed to delete the file " + name);
	}
	
	@Override
	public void write(String context) {
		if(context == null)
			throw new IllegalArgumentException("context == null");
		
		boolean result = (boolean) new WriteFile(file, context).execute();
		
		if (result) 
			System.out.println("File " + name + " written succesfully");
		else 
		    System.out.println("Failed to write the file " + name);
	}
	
	@Override
	public void read() {
		String result = (String) new ReadFile(file).execute();
		
		if (result == null) 
			System.out.println("Failed to read the file " + name);
		else 
		    System.out.println(result);
	}

	@Override
	public void readPartially(int begin, int end) {
		if(begin < 0 || end < 0)
			throw new IllegalArgumentException("begin < 0 || end < 0");
		if(begin >= end)
			throw new IllegalArgumentException("begin >= end");
		
		String result = (String) new ReadPartiallyFile(file, begin, end).execute();
		
		if (result == null) 
			System.out.println("Failed to read partially the file " + name);
		else 
		    System.out.println(result);
		
	}

	@Override
	public void union(EditableFile other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(int begin, int end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rename(String newName) {
		
		String oldName = name;
		
		File oldFile = new File(dir + file, "old_" + name);
		File newFile = new File(dir + oldFile, newName);
		
		boolean result = (boolean) new DeleteFile(file).execute();
		
		try {
			if(newFile.createNewFile() && result) {
				System.out.println("File " + name + " renamed succesfully");
				file = newFile;
				name = newName;
			} else {
				System.out.println("Failed to rename the file " + oldName);
				file = oldFile;
				name = oldName;
			}
			new DeleteFile(oldFile).execute();
			
		} catch (IOException e) {
			System.err.println("Impossibile rinominare file " + oldName + " " + e.getCause());
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

} // ! EditableFileImpl
