package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.concretes;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.File;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.FileSystemFactory;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.Folder;

public class FileSystemFactoryImpl implements FileSystemFactory{

	@Override
	public File createFile(String name) {
		return new FileImpl(name);
	}

	@Override
	public Folder createFolder(String name) {
		return new FolderImpl(name);
	}

}
