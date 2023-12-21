package it.unipr.informatica.design_patterns.creational.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Media;

public class Tape implements Media{
	private String sound_;
	
	protected Tape() {
		sound_ = null;
	}
	
	public void saveOnTape(String sound) {
		sound_ = sound;
	}
	public String readTape() {
		return sound_;
	}
	
}
