package it.unipr.informatica.design_patterns.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.Media;

public class Cd implements Media{
	private String sound_;
	
	protected Cd() {
		sound_ = null;
	}
	
	public void saveOnCd(String sound) {
		sound_ = sound;
	}
	public String readCd() {
		return sound_;
	}
	
}
