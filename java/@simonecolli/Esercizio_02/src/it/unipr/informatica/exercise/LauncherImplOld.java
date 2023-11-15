package it.unipr.informatica.exercise;

public class LauncherImplOld implements Launcher {
		
//	due launcher causano attesa
//	private boolean flag_ = false;
	
	@Override
	public void start(Task[] tasks) {
		
//		flag_ = false;
		System.out.println("************************* LauncherImplOld *************************");
		
		if (tasks == null)
			throw new IllegalArgumentException("tasks");
		
		
		Object mutex = new Object();
		
		
		
		
		synchronized (mutex) {
			
			for (int i = 0; i < tasks.length; i++) {
				
				int tmp = i;
			
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						
						tasks[tmp].perform();
								
						synchronized (mutex) {
							System.out.println("---- Task " + tmp + " notify ----");
							mutex.notifyAll();
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

}
