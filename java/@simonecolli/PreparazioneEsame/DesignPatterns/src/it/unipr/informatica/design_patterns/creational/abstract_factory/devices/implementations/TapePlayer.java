package it.unipr.informatica.design_patterns.creational.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Media;
import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Player;

public class TapePlayer implements Player{

	private Tape media_;
	protected TapePlayer() {
		media_ = null;
	}
	@Override
	public void accept(Media media) {
		media_ = (Tape) media;
	}

	@Override
	public void play() {
		if(media_ == null)
			System.out.println("missing tape");
		else
			System.out.println(media_.readTape());
	}

}
