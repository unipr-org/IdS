package exam_2023_02_07.lab.it.unipr.informatica.exam.dispatcher;

import java.util.Vector;

import exam_2023_02_07.lab.it.unipr.informatica.exam.abstracts.Actor;

public class ActorDispatcher {
	
	private volatile static ActorDispatcher INSTANCE = null;
	
	public static ActorDispatcher getInstance() {
		if(INSTANCE == null)
			synchronized (ActorDispatcher.class) {
				if(INSTANCE == null)
					INSTANCE = new ActorDispatcher();
			}
		return INSTANCE;
	}
	
	private Vector<Actor> actors_;
	private ActorDispatcher() {
		actors_ = new Vector<>();
	}
	
	public Actor getRandomActor() {
		return actors_.elementAt(
			(int)(Math.random() * actors_.size())
		);
	}
	public void addActor(Actor a) {
		actors_.add(a);
	}
	
	
}
