package it.unipr.informatica.aspects;

import it.unipr.informatica.aspects.model.LoggingAspect;
import it.unipr.informatica.blocking_queue.ArrayBlockingQueue;
import it.unipr.informatica.blocking_queue.model.BlockingQueue;

public class LoggingAspectTest {
	private void go() {
		try {
			BlockingQueue<String> q = LoggingAspect.attach(new ArrayBlockingQueue<String>(5)) ;
			q.isEmpty();
			q.isFull();
			q.remainingCapacity();
			
			q.put("ciao");
			q.put("mi");
			q.put("chiamo");
			
			q.toString();
			
			q.isEmpty();
			q.isFull();
			q.remainingCapacity();
			
			q.put("mattia");
			q.put("forza");
			
			q.toString();
			
			q.take();
			q.isEmpty();
			q.isFull();
			q.remainingCapacity();

			q.toString();
			
			q.clear();
			
			q.isEmpty();
			q.isFull();
			q.remainingCapacity();
		
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
	}
	
	public static void main(String[] args) {
		new LoggingAspectTest().go();
	}
}
