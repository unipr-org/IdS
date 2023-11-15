package it.unipr.informatica.exercise;

public class LauncherImplStartIndipendent implements Launcher {
	
	
	@Override
	public void start(Task[] tasks) {

		System.out.println("************************* LauncherImplStartIndipendent *************************");
		
		if (tasks == null)
			throw new IllegalArgumentException("tasks");
		
		
		Object mutex = new Object();
		MyFlag myFlag= new MyFlag();
				
		
		
		synchronized (mutex) {
			
			for (int i = 0; i < tasks.length; i++) {
				
				int tmp = i;
			
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						
						tasks[tmp].perform();
						
						synchronized (myFlag) {
							System.out.println("Task " + tmp +  " enter flagMutex");
							if(myFlag.flag_) {
								
								synchronized (mutex) {
									System.out.println("---- Task " + tmp + " notify ----");
									mutex.notifyAll();
								}
								myFlag.flag_ = false;
							}
							System.out.println("Task " + tmp +  " left flagMutex");
						}
					}
				};
				
				new Thread(runnable).start();
			}
		

			try {
				mutex.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		
	}
	
	private class MyFlag {
		private boolean flag_ = true;
	}

}
