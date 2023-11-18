package it.unipr.informatica.concurrent;

public class Test {
	public static void main(String[] args) {
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		
		queue.put(10);
		queue.put(20);
		queue.put(30);
		queue.put(40);
		queue.put(50);
				
		queue.printQueue();
		
		try {
			Integer x = queue.take();
			System.out.println("Estraggo: " + x);
		} catch (InterruptedException e) {
			System.out.println("Errore");
			return;
		}
		queue.printQueue();
		
		System.out.println("Capacita' rimanente: " + queue.remainingCapacity());
		queue.clear();
		
		queue.printQueue();
	}
}
