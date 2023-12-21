package it.unipr.informatica.design_patterns.behavioral.command.tv.controller;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;
import it.unipr.informatica.design_patterns.behavioral.command.tv.commands.*;

public class UniversalControllerBuilder implements ControllerBuilder {
	
	private TV tv_;
	private Command power_;
	private Command volumeUp_;
	private Command volumeDown_;
	private Command setChannel_;
	
//	singleton
	private static volatile UniversalControllerBuilder instance_;
	
	public static ControllerBuilder getInstance() {
		if(instance_ == null)
			synchronized (UniversalControllerBuilder.class) {
				if (instance_ == null) {
					instance_ = new UniversalControllerBuilder();
				}
			}
		return instance_;
	}
	
	private UniversalControllerBuilder () {
	}

	@Override
	public ControllerBuilder addTV(TV tv) {
		this.tv_ = tv;
		return this;
	}
	@Override
	public ControllerBuilder addPowerButton() {
		power_ = new TVPowerCommand(tv_); 
		return this;
	}

	@Override
	public ControllerBuilder addVolumeUpButton() {
		volumeUp_ = new TVVolumeUpCommand(tv_); 
		return this;
	}

	@Override
	public ControllerBuilder addVolumeDownButton() {
		volumeDown_ = new TVVolumeDownCommand(tv_); 
		return this;
	}

	@Override
	public ControllerBuilder addKeypad() {
		setChannel_ = new TVSetChannelCommand(tv_); 
		return this;		
	}

	@Override
	public Object getController() {
		return new UniversalController(power_, volumeUp_, volumeDown_, setChannel_);
	}
}
