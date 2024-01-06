package it.unipr.informatica.exams.exam_20230207.lab.abstractions;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface Resource {
	public int getID();

	public void acquire() throws InterruptedException;

	public void release();

	public int use();
}
