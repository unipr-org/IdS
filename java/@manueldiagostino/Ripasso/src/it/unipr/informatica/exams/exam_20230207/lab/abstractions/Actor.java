package it.unipr.informatica.exams.exam_20230207.lab.abstractions;

import it.unipr.informatica.exams.exam_20230207.lab.implementations.Message;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface Actor {
	public String getName();

	public void deliver(Message message);

	public void start();

	public void remove();
}
