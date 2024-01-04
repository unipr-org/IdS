package it.unipr.informatica.exams.exam_20230207.lab.implementations;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ResourceImpl implements Resource {
	private int id;
	private Object mutex;
	
	private boolean acquired;
	Thread currentOwner;
	
	public ResourceImpl(int id) {
		this.id = id;
		this.mutex = new Object();
		this.acquired = false;
	}
	
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void acquire() throws InterruptedException {
		if (Thread.currentThread() == currentOwner)
			throw new IllegalMonitorStateException("Thread.currentThread() == currentOwner");
		
		synchronized (mutex) {
			while (acquired)
				mutex.wait();
			
			
			acquired = true;
			currentOwner = Thread.currentThread();
		}
	}

	@Override
	public void release() {
		if (Thread.currentThread() != currentOwner)
			throw new IllegalMonitorStateException("Thread.currentThread() != currentOwner");
		
		synchronized (mutex) {
			acquired = false;
			currentOwner = null;
			
			mutex.notifyAll();
		}
	}

	@Override
	public int use() {
		int res = (int)(Math.random()*10);
		return res;
	}
	
	public static void main(String[] args) {
		Resource r0 = new ResourceImpl(0);
		
		for (int i=0; i<10; ++i) {
			Runnable runnable = () -> {
				try {
					r0.acquire();
					Thread.sleep(1000 + (int)(Math.random()*1340));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(r0.use());
				
				r0.release();
			};
			
			new Thread(runnable).start();
		}
	}

}
