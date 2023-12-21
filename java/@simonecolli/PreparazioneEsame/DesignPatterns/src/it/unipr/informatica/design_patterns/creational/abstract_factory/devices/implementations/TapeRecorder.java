package it.unipr.informatica.design_patterns.creational.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Media;
import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Recorder;

public class TapeRecorder implements Recorder{

	private Tape media_;
	protected TapeRecorder() {
		media_ = null;
	}
	@Override
	public void accept(Media media) {
		media_ = (Tape) media;
	}

	@Override
	public void record(String sound) {
		if(media_ == null)
			System.out.println("missing tape");
		
		media_.saveOnTape(sound);
	}

}
