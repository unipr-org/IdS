package it.unipr.informatica.design_patterns.creational.abstract_factory.devices.implementations;

import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.DeviceFactory;
import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Media;
import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Player;
import it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces.Recorder;

public class TapeDeviceFactory implements DeviceFactory{

	@Override
	public Media createMedia() {
		return new Tape();
	}

	@Override
	public Player createPlayer() {
		return new TapePlayer();
	}

	@Override
	public Recorder createRecorder() {
		return new TapeRecorder();
	}

}
