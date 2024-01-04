package it.unipr.informatica.exams.exam_20230207.lab.implementations;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Actor;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ActorsDispatcher;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ActorDispatcherImpl implements ActorsDispatcher {
	private Actor[] actors;

	public ActorDispatcherImpl(Actor[] actors) {
		if (actors == null)
			throw new IllegalArgumentException("actors == null");
		
		this.actors = actors;
	}
	
	public ActorDispatcherImpl() {		
		this.actors = null;
	}
	
	public void setActors(Actor[] actors) {
		if (actors == null)
			throw new IllegalArgumentException("actors == null");
		
		this.actors = actors;
	}
	
	@Override
	public Actor getRandomActor() {
		if (actors == null) 
			throw new IllegalMonitorStateException("actors == null");
		
		int k = (int)(Math.random()*actors.length);
		return actors[k];
	}

}
