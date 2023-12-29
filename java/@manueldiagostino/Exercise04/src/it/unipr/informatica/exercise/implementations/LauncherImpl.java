package it.unipr.informatica.exercise.implementations;

import it.unipr.informatica.exercise.abstractions.Launcher;
import it.unipr.informatica.exercise.abstractions.Monitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class LauncherImpl implements Launcher {

	@Override
	public Monitor launch(Runnable r) {
		Thread task = new Thread(r);
		task.start();
		
		return new MonitorImpl(task);
	}

}
