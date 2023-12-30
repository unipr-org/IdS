package it.unipr.informatica.examples.esempio2;

import it.unipr.informatica.concurrent.BlockingQueue;

public class Producer implements Runnable{
	private int id;
	private BlockingQueue<String> queue;
	private int time;
	
	public Producer(int id, BlockingQueue<String> queue, int time) {
		this.id = id;
		this.queue = queue;
		this.time = time;
	}
	
	@Override
	public void run() {
		for(int c = 0; c < 10; ++c) {
			try {
				String str = "Item" + id + "-" + c;
				
				System.out.println("Prod" + id + " prova a inviare");
				queue.put(str);
				System.out.println("Prod" + id + " invia: " + str);
				
				Thread.sleep(time); 
			} catch (InterruptedException e) {
				return;
			}
		}
		System.out.println("Prod" + id + " finito di inviare");
	}
	
}
