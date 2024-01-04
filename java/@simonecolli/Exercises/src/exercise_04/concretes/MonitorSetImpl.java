package exercise_04.concretes;

import java.util.Vector;

import exercise_04.abstracts.Monitor;
import exercise_04.abstracts.MonitorSet;

public class MonitorSetImpl implements MonitorSet {
	private Vector<Monitor> monitors_;
	private Object mutex_;
	private boolean isWaiting_;
	
	public MonitorSetImpl() {
		monitors_ = new Vector<>();
		mutex_ = new Object();
		isWaiting_ = false;
	}
	
	@Override
	public boolean add(Monitor m) {
		synchronized (monitors_) {
			return monitors_.add(m);			
		}
	}

	@Override
	public boolean await() throws InterruptedException {
		synchronized (monitors_) {
			if(monitors_.isEmpty())
				return true;			
		}
		
		synchronized (mutex_) {
			
			if(!isWaiting_) {
			
				isWaiting_ = true;
				
				for (Monitor monitor : monitors_) {
					new Thread(() -> {
							try {
								monitor.await();
								synchronized (monitors_) {
									monitors_.remove(monitor);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							synchronized (mutex_) {
								mutex_.notifyAll();
							}
							
					}).start();
				}
			}
			
			synchronized (monitors_) {
				if(monitors_.isEmpty())
					return true;
			}
			mutex_.wait();
		}
		return false;
		
	}

}
