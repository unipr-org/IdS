package it.unipr.informatica.pools;

public class PriorityQueueThreadPoolTest {
	public void test() {
		PriorityQueueThreadPool es = new PriorityQueueThreadPool(20);
		for (int i=0; i<5; ++i) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int j=0; j<18; ++j) {
				
				int i_f = i;
				int j_f = j;
				
				Runnable runnable = () -> {
					try {
						Thread.sleep(1500 + (int)(1500*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Thread [" + i_f + "]" + "[" + j_f + "]" + " interrupted");
					}
					System.out.println("Thread [" + i_f + "]" + "[" + j_f + "]");
				};
				
				PriorityQueueThreadPool.Priority priority = (j<7) ? PriorityQueueThreadPool.Priority.HIGH : PriorityQueueThreadPool.Priority.MEDIUM;
				if (j>10)
					priority =  PriorityQueueThreadPool.Priority.LOW;
				
				es.executeWithPriority(runnable, priority);
			}
		}
		
		try {
			Thread.sleep(20000);
			es.shutdown();
			System.out.println("Test terminated");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new PriorityQueueThreadPoolTest().test();
	}
}
