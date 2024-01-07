package it.unipr.informatica.pools;

import it.unipr.informatica.pools.model.ExecutorService;

public class Main {
	public void test() {
		ExecutorService es = Executors.newThreadPool(5);
		
		for(int i = 0; i < 15; i++) {
			int id = i;
			
			Runnable runnable = () -> {
//				System.out.println("Thread.currentThread().isInterrupted() - Task" + id + ": " + Thread.currentThread().isInterrupted());
				
				if(Thread.currentThread().isInterrupted())
					return;
					
				try {
					Thread.sleep(1000 + (int) Math.random() * 2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
//					System.out.println("Thread.currentThread().isInterrupted() - Task" + id + ": " + Thread.currentThread().isInterrupted());
//					System.out.println("Task" + id + " interrupted");
				}
				
				System.out.println("Task" + id + " finished");
			};
			
			es.execute(runnable);
			System.out.println("Task" + id + " added into queue");
		}
		
		es.shutdown();
		
//		es.execute(() -> {
//			System.out.println("Task" + 99);
//		});
		
	}
	
	public static void main(String[] args) {
		new Main().test();
	}
}
