package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.concretes;

import java.util.Vector;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.Folder;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.SystemComponent;

public class FolderImpl implements Folder {

	private Vector<SystemComponent> components_;
	private String name_;
	public FolderImpl(String name) {
		components_ = new Vector<>();
		name_ = name;
	}
	
	@Override
	public void add(SystemComponent component) {
		components_.add(component);
	}

	@Override
	public void remove(SystemComponent component) {
		components_.remove(component);
	}

	@Override
	public String getName() {
		return name_;
	}

	@Override
	public void setName(String name) {
		name_ = name;
	}

	@Override
	public void listContent(String tabs) {
		if(tabs.equals(""))
			System.out.println(" | " + name_);
		tabs += "\t";
		for (SystemComponent systemComponent : components_) {
			System.out.println(tabs + " - " + systemComponent.getName());
			if(systemComponent instanceof Folder)
				((Folder) systemComponent).listContent(tabs);
		}
	}

}
