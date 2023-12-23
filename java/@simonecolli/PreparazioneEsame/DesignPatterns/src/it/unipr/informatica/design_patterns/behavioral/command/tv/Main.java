package it.unipr.informatica.design_patterns.behavioral.command.tv;

import it.unipr.informatica.design_patterns.behavioral.command.tv.controller.Controller;
import it.unipr.informatica.design_patterns.behavioral.command.tv.controller.ControllerBuilder;
import it.unipr.informatica.design_patterns.behavioral.command.tv.controller.UniversalControllerBuilder;

public class Main {
	
	public static void main(String[] args) {
		TV tv = new TV();
		
		ControllerBuilder builder = UniversalControllerBuilder.getInstance();
		
		builder.addTV(tv).addPowerButton().addVolumeUpButton().addVolumeDownButton().addKeypad();
		
		Controller controller = (Controller) builder.getController();
		
		System.out.println("Controller created");
		
		controller.pressPowerButton();
		controller.pressPowerButton();
		controller.pressPowerButton();
		controller.pressKeypadToEnterNumber();
		controller.pressVolumeUpButton();
		controller.pressVolumeUpButton();
		controller.pressVolumeUpButton();
		controller.pressVolumeDownButton();		
		controller.pressPowerButton();
	}
}
