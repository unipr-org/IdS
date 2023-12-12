package it.unipr.informatica.aspects;

import it.unipr.informatica.blocking_queue.ArrayBlockingQueue;
import it.unipr.informatica.blocking_queue.BlockingQueue;

public class LoggingAspectTest {
	private void go() {
		try {
			BlockingQueue<String> q = LoggingAspect.attach(new ArrayBlockingQueue<String>(5)) ;
			
//			System.out.println("isEmpty(): " + q.isEmpty()); // True
//			System.out.println("isFull(): " + q.isFull()); // False
//			System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 5
		
			q.put("ciao");
			q.put("mi");
			q.put("chiamo");
			
//			System.out.println("isEmpty(): " + q.isEmpty()); // False
//			System.out.println("isFull(): " + q.isFull()); // False
//			System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 2
			q.print();
			
			q.put("mattia");
			q.put("forza");
			
//			System.out.println("isEmpty(): " + q.isEmpty()); // False
//			System.out.println("isFull(): " + q.isFull()); // True
//			System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 0
			q.print();
			
//			System.out.println("take(): " + q.take());
//			System.out.println("isEmpty(): " + q.isEmpty()); // False
//			System.out.println("isFull(): " + q.isFull()); // False
//			System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 1
			q.print();
			
			q.clear();
			
//			System.out.println("isEmpty(): " + q.isEmpty()); // True
//			System.out.println("isFull(): " + q.isFull()); // False
//			System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 5
		
		} catch (InterruptedException e) {
			System.err.println(e.getCause());
		}
	}
	
	public static void main(String[] args) {
		new LoggingAspectTest().go();
	}
}
