package it.unipr.informatica.blocking_queue;

public class Main {
	private void go() throws InterruptedException{
		  BlockingQueue<String> q = new ArrayBlockingQueue<String>(5);
		  
		  System.out.println("isEmpty(): " + q.isEmpty()); // True
		  System.out.println("isFull(): " + q.isFull()); // False
		  System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 5
		  
		  q.put("ciao");
		  q.put("mi");
		  q.put("chiamo");
		  
		  System.out.println("isEmpty(): " + q.isEmpty()); // False
		  System.out.println("isFull(): " + q.isFull()); // False
		  System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 2
		  System.out.println(q);
		  
		  q.put("mattia");
		  q.put("forza");
		  
		  System.out.println("isEmpty(): " + q.isEmpty()); // False
		  System.out.println("isFull(): " + q.isFull()); // True
		  System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 0
		  System.out.println(q);
		  
		  System.out.println("take(): " + q.take());
		  System.out.println("isEmpty(): " + q.isEmpty()); // False
		  System.out.println("isFull(): " + q.isFull()); // False
		  System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 1
		  System.out.println(q);
		  
		  q.clear();
		  
		  System.out.println("isEmpty(): " + q.isEmpty()); // True
		  System.out.println("isFull(): " + q.isFull()); // False
		  System.out.println("remainingCapacity(): " + q.remainingCapacity()); // 5
		 }
	public static void main(String[] args) {
		try {
			new Main().go();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
