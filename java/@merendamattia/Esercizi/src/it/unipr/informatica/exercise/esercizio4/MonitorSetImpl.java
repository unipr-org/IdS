package it.unipr.informatica.exercise.esercizio4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MonitorSetImpl implements MonitorSet {
	private List<MonitorImpl> list;
	private Thread exec;
	
	public MonitorSetImpl() {
		this.list = new ArrayList<MonitorImpl>();
		this.exec = null;
	}
	
	@Override
	public boolean add(MonitorImpl m) {
		return list.add(m);
	}

	@Override
	public void await() throws InterruptedException {
		exec = new InnerThreadAwait();
		exec.start();
	}

	@Override
	public void awaitAll() throws InterruptedException {
		exec = new InnerThreadAwaitAll();
		exec.start();
	}
	
	private class InnerThreadAwait extends Thread {
		@Override
		public void run() {
			boolean done = false;
			
			while(!done) {
				synchronized (list) {
					Iterator<MonitorImpl> it = list.iterator();
					
					while(it.hasNext()) {
						
						MonitorImpl m = it.next();
//						System.out.println(m + " m.isDone():" + m.isDone());
						
						if(m.isDone()) {
							done = true;
							System.out.printf("[MonitorSet] notificato da %s - %s%n", m, Thread.currentThread());
							return;
						}
					}
				}
				
			}
			interrupt();
		}
	} // ! InnerThreadAwait
	
	private class InnerThreadAwaitAll extends Thread {
		@Override
		public void run() {
			List<MonitorImpl> coll = new ArrayList<MonitorImpl>();
			int dim = 0;
			
			while(coll.size() < list.size()) {
				synchronized (coll) {
					for(MonitorImpl m : list) {
						if(m.isDone() && !coll.contains(m)) {
							coll.add(m);
						}
					}
				}
			}
			System.out.printf("[MonitorSet] notificato da tutti i Monitor");
			interrupt();
		}
	} // ! InnerThreadAwaitAll
	
} // ! MonitorSetImpl
