package it.unipr.informatica.exercise;

import java.util.HashSet;
import java.util.Set;

public class MonitorSetImpl implements MonitorSet {
	private Set<Monitor> monitors;
	
	private Object mutex;
	
	public MonitorSetImpl() {
		this.monitors = new HashSet<>();

		this.mutex = new Object();
	}
	
	@Override
	public boolean add(Monitor m) {
		synchronized (mutex) {
			if (monitors.contains(m))
				return false;
			
			monitors.add(m);

			new Thread(() -> {
				try {
					m.await();
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
				
				synchronized (mutex) {
					monitors.remove(m);
					
					mutex.notify();
				}
			}).start();
			
			return true;
		}
	}

	@Override
	public void await() throws InterruptedException {
		synchronized (mutex) {
			if (!monitors.isEmpty())
				mutex.wait();
		}
	}
}
