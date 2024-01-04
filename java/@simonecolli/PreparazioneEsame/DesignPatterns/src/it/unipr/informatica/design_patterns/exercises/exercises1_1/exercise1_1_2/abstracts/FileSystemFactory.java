package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts;

public interface FileSystemFactory {
	public File createFile(String name);
	public Folder createFolder(String name);
}
