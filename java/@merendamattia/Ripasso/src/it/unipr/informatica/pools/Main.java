package it.unipr.informatica.pools;

public class Main {
	public void test() {
		ExecutorService es = Executors.newThreadPool(6);
		
		for(int i = 0; i < 15; i++) {
			int id = i;
			
			Runnable runnable = () -> {
				try {
					Thread.sleep(1000 + (int) Math.random() * 2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Task" + id + " finished");
			};
			
			es.execute(runnable);
		}
		
		es.shutdown();
//		
//		es.execute(() -> {
//			System.out.println("Task" + 99);
//		});
		
	}
	
	public static void main(String[] args) {
		new Main().test();
	}
}
