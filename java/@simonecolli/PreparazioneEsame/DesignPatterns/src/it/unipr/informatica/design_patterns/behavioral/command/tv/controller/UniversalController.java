package it.unipr.informatica.design_patterns.behavioral.command.tv.controller;

import it.unipr.informatica.design_patterns.behavioral.command.tv.commands.Command;

public class UniversalController implements Controller{
	private Command power_;
	private Command volumeUp_;
	private Command volumeDown_;
	private Command setChannel_;
	
	public UniversalController(Command power, Command volumeUp , Command volumeDown, Command setChannel) {
		this.power_ = power;
		this.volumeUp_ = volumeUp;
		this.volumeDown_ = volumeDown;
		this.setChannel_ = setChannel;
	}
	
	
	@Override
	public void pressPowerButton() {
		power_.execute();
	}
	@Override
	public void pressVolumeUpButton() {
		volumeUp_.execute();
	}
	@Override
	public void pressVolumeDownButton() {
		volumeDown_.execute();
	}
	@Override
	public void pressKeypadToEnterNumber() {
		setChannel_.execute();
	}
}
