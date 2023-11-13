package it.unipr.informatica.exercise;

public class LauncherImpl implements Launcher {
	
//	due launcher causano attesa
//	private boolean flag_ = false;
	
	@Override
	public void start(Task[] tasks) {
		
//		flag_ = false;
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
	//						flag_ = true;
							mutex.notifyAll();		
						}
	
					}
				};
				new Thread(runnable).start();
			}
		
//		if(!flag_)
			try {
				mutex.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		
	}

}
