package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.FileSystemFactory;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.Folder;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.concretes.FileSystemFactoryImpl;

public class Main {
	public static void main(String[] args) {
		FileSystemFactory factory = new FileSystemFactoryImpl();
		Folder homeFolder = factory.createFolder("home");
		Folder documents = factory.createFolder("Documents");
		homeFolder.add(documents);
		homeFolder.add(factory.createFolder("Desktop"));
		homeFolder.listContent("");
		documents.add(factory.createFile("prova"));
		
		homeFolder.listContent("");
	}
}
