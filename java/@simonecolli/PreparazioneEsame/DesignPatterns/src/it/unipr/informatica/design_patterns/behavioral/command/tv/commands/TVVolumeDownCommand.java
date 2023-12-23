package it.unipr.informatica.design_patterns.behavioral.command.tv.commands;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;

public class TVVolumeDownCommand implements Command{
	
	private TV tv_;
	public TVVolumeDownCommand(TV tv) {
		this.tv_ = tv;
	}
	@Override
	public void execute() {
		tv_.volumeDown();
	}

}
