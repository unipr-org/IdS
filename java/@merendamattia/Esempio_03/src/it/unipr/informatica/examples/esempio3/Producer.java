package it.unipr.informatica.examples.esempio3;

import it.unipr.informatica.concurrent.BlockingQueue;

public class Producer implements Runnable{
	private int id;
	private BlockingQueue<String> queue;
	
	public Producer(int id, BlockingQueue<String> queue) {
		this.id = id;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int c = 0; c < 10; ++c) {
			try {
				String str = "Item" + id + "-" + c;
				
				System.out.println("Prod" + id + " prova a inviare");
				queue.put(str);
				System.out.println("Prod" + id + " invia: " + str);
				
				Thread.sleep(100 + (int) (50 * Math.random()));
			} catch (InterruptedException e) {
				return;
			}
		}
		System.out.println("Prod" + id + " finito di inviare");
	}
	
}
