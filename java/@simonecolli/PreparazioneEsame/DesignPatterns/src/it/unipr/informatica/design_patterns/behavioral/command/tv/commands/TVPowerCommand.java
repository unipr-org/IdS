package it.unipr.informatica.design_patterns.behavioral.command.tv.commands;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;

public class TVPowerCommand implements Command{
	
	private TV tv_;
	public TVPowerCommand(TV tv) {
		this.tv_ = tv;
	}
	@Override
	public void execute() {
		tv_.power();
	}

}
