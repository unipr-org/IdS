package it.unipr.informatica.examples.esempio3;

import it.unipr.informatica.concurrent.ArrayBlockingQueue;
import it.unipr.informatica.concurrent.BlockingQueue;

public class Example03 {
	private int n = 5; // Numero di Consumer e Producer
	
	private void go() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		
		try {
			queue.put("Default1");
			queue.put("Default2");
			queue.put("Default3");
		} catch (InterruptedException e) {
			return;
		}
		
//		System.out.print("Coda iniziale = ");
//		queue.printQueue();
//		System.out.println();
		
		for(int i = 0; i < n; ++i) {
			Consumer consumer = new Consumer(i, queue);
			new Thread(consumer).start();
		}
		
		for(int i = 0; i < n; ++i) {
			Producer producer = new Producer(i, queue);
			new Thread(producer).start();
		}
	}
	
	public static void main(String[] args) {
		new Example03().go();
	}
}
