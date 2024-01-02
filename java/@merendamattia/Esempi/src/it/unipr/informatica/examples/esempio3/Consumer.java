package it.unipr.informatica.examples.esempio3;

import it.unipr.informatica.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	private int id;
	private BlockingQueue<String> queue;
	
	public Consumer(int id, BlockingQueue<String> queue) {
		this.id = id;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int c = 0; c < 15; ++c) {
			try {
				System.out.println("Cons" + id + " prova a consumare");
				// queue.printQueue(); // Stampo la coda
				String str = queue.take(); // Estraggo
				System.out.println("Cons" + id + " consuma: " + str);
				
				Thread.sleep(40 + (int) (100 * Math.random())); 
			} catch (InterruptedException e) {
				return;
			}
			
		}
		
	}
	
}
