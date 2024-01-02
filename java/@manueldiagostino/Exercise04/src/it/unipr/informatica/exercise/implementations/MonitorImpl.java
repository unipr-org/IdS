package it.unipr.informatica.exercise.implementations;

import it.unipr.informatica.exercise.abstractions.Monitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class MonitorImpl implements Monitor {
	private Object mutex;
	private boolean isDone;
	private boolean isMonitoring;
	
	public MonitorImpl(Thread target) {
		if (target == null)
			throw new IllegalArgumentException("mutex == null");
		
		this.isDone = false;
		this.isMonitoring = false;
		this.mutex = new Object();
		
		Runnable runnable = () -> {
			synchronized (mutex) {
				isMonitoring = true;
			}
			
			try {
				do {
					Thread.sleep(1000);
				} while(target.isAlive());
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (mutex) {
				isDone = true;
				mutex.notifyAll();
			}
		};
		
		new Thread(runnable).start();
	}

	@Override
	public void await() throws InterruptedException {
		synchronized (mutex) {
			while (!isMonitoring)
				mutex.wait();
			
			while (!isDone)
				mutex.wait();
		}
	}
	
	@Override
	public int hashCode() {
		return mutex.hashCode();
	}

	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			System.out.println("Thread started");
			try {
				System.out.println("Thread sleeping");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread exiting");
		};
		
		System.out.println("Main started");
		Thread t = new Thread(task);
		t.start();
		
		Monitor m = new MonitorImpl(t);
		System.out.println("Main waiting");
		m.await();
		System.out.println("Main exiting");
	}
}