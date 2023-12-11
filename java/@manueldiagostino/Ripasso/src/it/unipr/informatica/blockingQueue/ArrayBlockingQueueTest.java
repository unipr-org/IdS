package it.unipr.informatica.blockingQueue;

public class ArrayBlockingQueueTest {
	
	private void go() {
		try {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
		
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		
		queue.put(1);
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		queue.print();
		
		queue.put(2);
		queue.put(3);
		queue.put(4);
		queue.put(5);
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		queue.print();
		
		queue.take();
		queue.take();
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		queue.print();
		
		queue.put(6);
		queue.put(7);
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		queue.print();
		// queue.put(8); // Si blocca
		
		queue.clear();
		System.out.println("isFull(): " + queue.isFull());
		System.out.println("isEmpty(): " + queue.isEmpty());
		System.out.println("remainingCapacity(): " + queue.remainingCapacity());
		queue.print();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public void concurrencyTest() {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		
		Runnable runnable = () -> {
			try {
				for (int i=0; i<2; ++i) {
					Thread.sleep(2000);
					queue.put(i);					
					queue.print();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		new Thread(runnable).start();
		new Thread(runnable).start();
		System.out.println("Parent terminated");
	}

	public static void main(String[] args) {
//		new ArrayBlockingQueueTest().go();
		new ArrayBlockingQueueTest().concurrencyTest();
		
	}
}
