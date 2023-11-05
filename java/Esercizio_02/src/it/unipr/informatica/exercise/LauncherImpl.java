package it.unipr.informatica.exercise;

public class LauncherImpl implements Launcher {
	@Override
	public void start(Task[] tasks) {
		if(tasks == null)
			throw new IllegalArgumentException("tasks");
		
		Object mutex = new Object(); // Semaforo
		Flag flag = new Flag();
		
		for(int i = 0; i < tasks.length; ++i) {
			
			int id = i;
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					tasks[id].perform();
					
					synchronized (mutex) {
						flag.notifyDone = true;
						
						mutex.notifyAll();
					}
				}
			};
			
			new Thread(runnable).start();
		}
		
		synchronized (mutex) {
			try {
				if(!flag.notifyDone)
					mutex.wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		
	}
	
	// Faccio questa inner class per evitare di aggiungere 
	// uno stato alla classe LauncherImpl
	//
	// `final` vuol dire che il riferimento a cui punta non cambia, ma il valore puo'
	// questo non vale pero' per i tipi primitivi
	private class Flag {
		private boolean notifyDone = false;
	}
}
