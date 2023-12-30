package it.unipr.informatica.exams.exam_20230207.lab;

public class Actor {	
	private int ID;
	private Actor[] actors;
	private Thread exec;
	private boolean shutdown;
	private Dispatcher dispatcher;

	public Actor(int ID) {
		this.ID = ID;
		this.exec = new MainExecution();
		this.shutdown = false;
	}
	
	public void setActors(Actor[] actors) {
		this.actors = actors;
	}
	
	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	public void deliver(Message message) {
		System.out.println("[Actor" + ID + "] message received: " + message.getValue());
		
		if(message.getValue() < 1)
			return;
		
		synchronized (message) {
			int rand = (int) (Math.random() * actors.length);
			actors[rand].deliver(new Message(message.getValue() - 1));
		}
	}
	
	public void start() {
		exec.start();
	}
	
	public void stop() {
		shutdown = true;
	}
	
	private class MainExecution extends Thread {
		@Override
		public void run() {
			
			System.out.println("[Actor" + ID + " Thread] started");
			
			while(!shutdown) {
				
				int K = (int) (Math.random() * 10) + 1;
				System.out.println("[Actor" + ID + " Thread] try to get " + K + " resources");
				
				Resource[] resources = dispatcher.acquireResources(K);
				
				System.out.println("[Actor" + ID + " Thread] get " + K + " resources");
				
				Resource[] resourcesUsed = new Resource[K];
				
				int S = 0;
				
				for(int i = 0; i < K; i++) {
					Resource item = resources[i];
					S += item.use();
					resourcesUsed[i] = item;
				}
				
				try {
					Thread.sleep(S);
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				}
				
				synchronized (actors) {
					int rand = (int) (Math.random() * actors.length);
					actors[rand].deliver(new Message(S));
				}
				
				dispatcher.releaseResources(resourcesUsed);
			}
			
			System.out.println("[Actor" + ID + " Thread] terminated");
			interrupt();
			
		}
	} // ! MainExecution

} // ! ActorImpl
