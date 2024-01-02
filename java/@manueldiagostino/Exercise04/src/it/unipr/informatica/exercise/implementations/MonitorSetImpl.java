package it.unipr.informatica.exercise.implementations;

import java.security.DomainCombiner;
import java.util.HashSet;

import it.unipr.informatica.exercise.abstractions.Monitor;
import it.unipr.informatica.exercise.abstractions.MonitorSet;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class MonitorSetImpl implements MonitorSet {
	private HashSet<Monitor> monitorSet;
	
	public MonitorSetImpl() {
		monitorSet = new HashSet<Monitor>();
	}
	
	@Override
	public boolean add(Monitor m) {
		return monitorSet.add(m);
	}

	@Override
	public void await() throws InterruptedException {
		
		synchronized (monitorSet) {
//			System.out.println("MonitorSet acquired mutex");
			
			for (Monitor m : monitorSet) {
				Runnable runnable = () -> {
//					System.out.println("Worker waiting");
					try {
						m.await();
					} catch (InterruptedException e) {
						System.out.println("MonitorSetImpl: interrupted worker");
						return;
					}
						
					synchronized (monitorSet) {
//						System.out.println("Worker notified");
						monitorSet.notifyAll();
					}
				};
				
				new Thread(runnable).start();
			}
			
			
//			System.out.println("MonitorSet waiting");
			monitorSet.wait();
//			System.out.println("MonitorSet exiting await()");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MonitorSetImpl ms = new MonitorSetImpl();
		Thread[] threads = new Thread[10];
		for (int i=0; i<threads.length; ++i) {
			int id = i;
			
			Runnable r = () -> {
				System.out.println("[Thread " + id + "] started");
				try {
					Thread.sleep((int)(Math.random()*5500) + 1500);
				} catch (InterruptedException e) {
					System.out.println("[Thread " + id + "] interrupted");
				}
				System.out.println("[Thread " + id + "] exited");
			};
			
			threads[i] = new Thread(r);
			ms.add(new MonitorImpl(threads[i]));
			threads[i].start();
		}
		
		ms.await();
		System.out.println("Main exited");
		
	}

}
