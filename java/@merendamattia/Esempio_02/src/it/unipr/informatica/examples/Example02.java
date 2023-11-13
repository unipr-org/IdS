package it.unipr.informatica.examples;

import it.unipr.informatica.concurrent.BlockingQueue;
import it.unipr.informatica.concurrent.LinkedBlockingQueue;

public class Example02 {
	private int n = 1; // Numero di Consumer e Producer
	private int timeP = 2500; // Polling time Producer (in millis)
	private int timeC = 2000; // Polling time Consumer (in millis)
	
	private void go() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		try {
			queue.put("Default1");
//			queue.put("Default2");
//			queue.put("Default3");
		} catch (InterruptedException e) {
			return;
		}
		
		System.out.println("Polling time Consumer: " + timeC);
		System.out.println("Polling time Producer: " + timeP);
		System.out.print("Coda iniziale = ");
		queue.printQueue();
		System.out.println();
		
		for(int i = 0; i < n; ++i) {
			Consumer consumer = new Consumer(i, queue, timeC);
			new Thread(consumer).start();
		}
		
		for(int i = 0; i < n; ++i) {
			Producer producer = new Producer(i, queue, timeP);
			new Thread(producer).start();
		}
	}
	
	public static void main(String[] args) {
		new Example02().go();
	}
}
