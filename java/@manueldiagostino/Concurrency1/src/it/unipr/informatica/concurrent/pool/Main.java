package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	private void go1() {
		System.out.println("<Entering go1()>");
		int size = 10;
		ExecutorService executors = Executors.newFixedThreadPool(size);
		
		for (int i=0; i<size; ++i) {
			int id = i;
			
			Runnable command = () -> {
				System.out.println("Task[" + id + "] started");
				
				try {
					Thread.sleep((long) (1000 + 1841*Math.random()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Task[" + id + "] exited");
			};
			
			executors.execute(command);
		}
		
		try {
			Thread.sleep(10000);
			executors.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void go2() {
		System.out.println("<Entering go2()>");
		int size = 10;
		ExecutorService executors = Executors.newFixedThreadPool(size);
		
		for (int i=0; i<size; ++i) {
			int id = i;
			
			Callable<Void> callable = () -> {
				System.out.println("Task[" + id + "] started");
				
				try {
					Thread.sleep((long) (1000 + 1841*Math.random()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Task[" + id + "] exited");
				return null;
			};
			
			Callback<Void> callback = (Void) -> {
				System.out.println("Task[" + id + "] callback");
			};
			
			executors.submit(callable, callback);
		}
		
		try {
			Thread.sleep(10000);
			executors.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main().go2();
		new Main().go1();
	}
}
