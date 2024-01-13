package it.unipr.informatica.exams.exam_20230126.model;

import it.unipr.informatica.exams.exam_20230126.AgentException;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Agent extends Runnable {
	public int getID();
	public double getState();
	public double interact(double state) throws AgentException;
	public void stop();
}
