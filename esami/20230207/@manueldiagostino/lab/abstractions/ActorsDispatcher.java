package it.unipr.informatica.exams.exam_20230207.lab.abstractions;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ActorsDispatcher {
	public void setActors(Actor[] actors);
	public Actor getRandomActor();
}
