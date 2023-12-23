package it.unipr.informatica.design_patterns.behavioral.command.tv.commands;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;

public class TVVolumeUpCommand implements Command{
	
	private TV tv_;
	public TVVolumeUpCommand(TV tv) {
		this.tv_ = tv;
	}
	@Override
	public void execute() {
		tv_.volumeUp();
	}

}
