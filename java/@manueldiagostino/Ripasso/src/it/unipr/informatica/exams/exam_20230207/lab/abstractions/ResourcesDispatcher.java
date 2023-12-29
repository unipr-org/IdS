package it.unipr.informatica.exams.exam_20230207.lab.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ResourcesDispatcher {
	public Resource[] getResources(int k) throws InterruptedException;
	public void setResources(Resource[] resources);
	public void releaseResources(Resource[] resources);
}
