package it.unipr.informatica.design_patterns.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.Media;
import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.Player;

public class CdPlayer implements Player{

	private Cd media_;
	protected CdPlayer() {
		media_ = null;
	}
	@Override
	public void accept(Media media) {
		media_ = (Cd) media;
	}

	@Override
	public void play() {
		if(media_ == null)
			System.out.println("missing tape");
		else
			System.out.println(media_.readCd());
	}

}
