package it.unipr.informatica.design_patterns.behavioral.command.tv.controller;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;

public interface ControllerBuilder {
	
	public ControllerBuilder addTV(TV tv);
	public ControllerBuilder addPowerButton();
	public ControllerBuilder addVolumeUpButton();
	public ControllerBuilder addVolumeDownButton();
	public ControllerBuilder addKeypad();
	
	public Object getController();
	
}
