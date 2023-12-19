package it.unipr.informatica.design_patterns.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.Media;
import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.Recorder;

public class CdRecorder implements Recorder{
	
	private Cd media_;
	
	protected CdRecorder() {
		media_ = null;
	}
	@Override
	public void accept(Media media) {
		media_ = (Cd) media;
	}

	@Override
	public void record(String sound) {
		media_.saveOnCd(sound);
	}

}
