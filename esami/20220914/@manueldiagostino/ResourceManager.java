package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ResourceManager {
	public void acquireResource(int i, int j) throws InterruptedException;
	public void freeResource(int i, int j);
}
