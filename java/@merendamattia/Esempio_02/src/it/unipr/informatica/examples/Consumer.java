package it.unipr.informatica.examples;

import it.unipr.informatica.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	private int id;
	private BlockingQueue<String> queue;
	private int time;
	
	public Consumer(int id, BlockingQueue<String> queue, int time) {
		this.id = id;
		this.queue = queue;
		this.time = time;
	}
	
	@Override
	public void run() {
		for(int c = 0; c < 15; ++c) {
			try {
				System.out.print("Cons" + id + " prova a consumare, queue = ");
				queue.printQueue(); // Stampo la coda
				String str = queue.take(); // Estraggo
				System.out.println("Cons" + id + " consuma: " + str);
				
				Thread.sleep(time); 
			} catch (InterruptedException e) {
				return;
			}
			
		}
		
	}
	
}
