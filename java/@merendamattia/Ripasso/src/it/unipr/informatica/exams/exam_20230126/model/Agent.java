package it.unipr.informatica.exams.exam_20230126.model;

public interface Agent extends Runnable {
	public int getID();
	public double getState();
	public double interact(double state) throws AgentException;
	public void stop();
}
