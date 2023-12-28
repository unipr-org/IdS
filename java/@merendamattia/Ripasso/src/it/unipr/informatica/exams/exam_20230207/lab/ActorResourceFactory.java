package it.unipr.informatica.exams.exam_20230207.lab;

public class ActorResourceFactory {
	private static ActorResourceFactory INSTANCE = null;
	private ActorResourceFactory() {}
	public ActorResourceFactory getInstance() {
		if(INSTANCE == null) {
			synchronized (ActorResourceFactory.class) {
				if(INSTANCE == null)
					INSTANCE = new ActorResourceFactory();
			}
		}
		return INSTANCE;
	}
	
	public static BlockingQueue<Resource> createResources(int num_resources){
		BlockingQueue<Resource> res = new ArrayBlockingQueue<Resource>(num_resources);
		for(int i = 0; i < num_resources; i++)
			res.put(new ResourceImpl(i));
		return res;
	}
	
	public static Actor[] createActors(int num_actors) {
		Actor[] actors = new Actor[num_actors];
		for(int i = 0; i < num_actors; i++)
			actors[i] = new Actor(i);
		
		for(int i = 0; i < num_actors; i++)
			actors[i].setActors(actors);
		
		return actors;
	}
} // ! ActorResourceFactory
