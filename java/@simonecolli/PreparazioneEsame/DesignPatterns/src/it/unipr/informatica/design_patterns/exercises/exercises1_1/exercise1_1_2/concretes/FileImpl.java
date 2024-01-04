package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.concretes;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.File;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts.SystemComponent;

public class FileImpl implements File{
	
	private String name_;
	private String content_;
	public FileImpl(String name) {
		name_ = name;
		content_ = "";
	}
	
	@Override
	public void add(SystemComponent component) {
		throw new RuntimeException("Can't add element to file");
	}

	@Override
	public void remove(SystemComponent component) {
		throw new RuntimeException("Can't remove element to file");		
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
	public void printContent() {
		System.out.println("Content of " + name_);
		System.out.println(content_);
	}

	@Override
	public void setContent(String content) {
		content_ = content;
	}

}
