package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface Resource {
	public boolean isAcquired();

	public void acquire() throws InterruptedException;

	public void release();
}
