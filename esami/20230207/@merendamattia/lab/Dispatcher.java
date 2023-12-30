package it.unipr.informatica.exams.exam_20230207.lab;

public class Dispatcher {
	private BlockingQueue<Resource> resources;
	private final int NUM_ACTORS;
	private final int NUM_RESOURCES;
	private Object mutex;
	
	public Dispatcher(int num_actors, int num_resources) {
		if(num_actors < 1 || num_resources < 1)
			throw new IllegalArgumentException("num_actors < 1 || num_resources < 1");
		
		this.NUM_ACTORS = num_actors;
		this.NUM_RESOURCES = num_resources;
		this.mutex = new Object();
		this.resources = ActorResourceFactory.createResources(num_resources);
	}
	
	public Resource[] acquireResources(int num_resources) {
		synchronized (mutex) {
			try {
				
				System.out.println("[INFO] resources avaiable: " + resources.size() + ", resources requested: " + num_resources);
				
				while(resources.size() < num_resources)
					mutex.wait();
			} catch (InterruptedException e) {
				System.out.println(e.getCause());
			}
			
			Resource[] res = new Resource[num_resources];
			
			System.out.println("[ACQUIRE] Resources avaiable (before): " + resources.size());
			
			for(int i = 0; i < num_resources; i++)
				res[i] = resources.take();
			
			System.out.println("[ACQUIRE] Resources avaiable (after): " + resources.size());
			
			return res;
		}
	}
	
	public void releaseResources(Resource[] item) {
		synchronized (mutex) {
			System.out.println("[RELEASE] Resources avaiable (before): " + resources.size());
			
			for(int i = 0; i < item.length; i++)
				resources.put(item[i]);
			
			System.out.println("[RELEASE] Resources avaiable (after): " + resources.size());
			
			mutex.notifyAll();
		}
	}
	
} // ! Dispatcher
