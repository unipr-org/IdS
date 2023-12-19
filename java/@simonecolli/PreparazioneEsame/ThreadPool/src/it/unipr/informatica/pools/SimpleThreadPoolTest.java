package it.unipr.informatica.pools;

public class SimpleThreadPoolTest {

	public void test() {
		ExecutorService executorService = Executors.newThreadPool(2);
		System.err.println("executors created");
		for(int i = 0; i < 10; ++i) {
			int tmp = i;
			
			Runnable runnable = () -> {
				
//				@Override
//				public void run() {
					try {
						Thread.sleep((long) (2000 + Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(tmp + "end");
//				}
			};
			System.err.println(i + " started");
			executorService.execute(runnable);
		}
		System.err.println("end");
		executorService.shutdown();
		
	}
	public static void main(String[] args) {
		new SimpleThreadPoolTest().test();
	}

}
