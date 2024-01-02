package it.unipr.informatica.exercise.esercizio4;

public class LauncherImpl implements Launcher {

	@Override
	public MonitorImpl launch(Runnable r, int ID) {
		if(r == null)
			throw new IllegalArgumentException("r == null");
		
		MonitorImpl monitor = new MonitorImpl(ID);
		
		Runnable runnable = () -> {
			r.run();
			synchronized (monitor) {
				monitor.signal();
			}
		};
		
		new Thread(runnable).start();
		
		return monitor;
	}

} // ! LauncherImpl
