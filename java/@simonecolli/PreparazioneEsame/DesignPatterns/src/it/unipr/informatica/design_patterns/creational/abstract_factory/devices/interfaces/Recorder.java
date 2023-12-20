package it.unipr.informatica.design_patterns.creational.abstract_factory.devices.interfaces;

public interface Recorder {
	public void accept(Media media);
	public void record(String sound);
}
