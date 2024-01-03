package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ResourceImpl implements Resource {
	private Object mutex;
	Thread currentThread;
	private boolean isAcquired;
	
	public ResourceImpl() {
		mutex = new Object();
		isAcquired = false;
		currentThread = null;
	}
	
	@Override
	public boolean isAcquired() {
		synchronized (mutex) {
			return isAcquired;
		}
	}

	@Override
	public void acquire() throws InterruptedException {
		synchronized (mutex) {
			while (isAcquired)
				mutex.wait();
			
			Thread thread = Thread.currentThread();
			
			if (currentThread != null)
				throw new IllegalMonitorStateException("currentThread != null");
				
			currentThread = thread;
			isAcquired = true;
		}
	}

	@Override
	public void release() {
		synchronized (mutex) {
			if (Thread.currentThread() != currentThread)
				throw new IllegalMonitorStateException("Thread.currentThread() != currentThread");
			
			isAcquired = false;
			currentThread = null;
			
			mutex.notifyAll();
		}
	}

}
