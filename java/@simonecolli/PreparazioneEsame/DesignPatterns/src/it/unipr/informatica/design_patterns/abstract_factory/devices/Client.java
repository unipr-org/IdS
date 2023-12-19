package it.unipr.informatica.design_patterns.abstract_factory.devices;

import it.unipr.informatica.design_patterns.abstract_factory.devices.interfaces.*;

public class Client {
	private DeviceFactory factory_;
	
	public void selectTecnology(DeviceFactory df) {
		if(df == null)
			throw new IllegalArgumentException("df == null");
		factory_ = df;
	}
	
	public void test(String song) {
		Media media = factory_.createMedia();
		Recorder recorder = factory_.createRecorder();
		Player player = factory_.createPlayer();
		
		recorder.accept( media );
		System.out.println( "Recording the song : " + song );
		
		recorder.record( song );
		System.out.println( "Listening the record:" );
		
		player.accept( media );
		player.play();
	}
	
}
