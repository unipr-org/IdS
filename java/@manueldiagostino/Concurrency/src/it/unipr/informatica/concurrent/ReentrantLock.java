package it.unipr.informatica.concurrent;

public class ReentrantLock implements Lock {
	private Object mutex = new Object();
	
	private int counter = 0; // necessario per lock rientrante
	private Thread owner = null; // visto che è rientrante bisogna conoscere il possessore
	
	/*
	 * Questo metodo mette in attesa tutti i thread diversi dall'owner che non 
	 * riescono ad ottenere il lock e quindi la mutua esclusione
	 * sulle condition.
	 * */
	@Override
	public void lock() {
		Thread currentThread = Thread.currentThread();
		
		synchronized (mutex) {
			if (counter < 0)
				throw new IllegalMonitorStateException("counter < 0");
			
			while (owner != null && owner != currentThread) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (owner == null)
				owner = currentThread; // acquisisce il lock
			
			++counter;
		}
		
	}

	@Override
	public void unlock() {
		Thread currentThread = Thread.currentThread();
		
		synchronized (mutex) {
			if (owner != currentThread)
				throw new IllegalMonitorStateException("unlock: owner != currentThread");
			
			if (counter <= 0)
				throw new IllegalMonitorStateException("unlock: counter <= 0");
			
			--counter;
			
			if (counter == 0) { // devo controllare counter==0 perchè è rientrante
				owner = null;
				mutex.notify();
			}
		}
		
	}

	@Override
	public Condition newCondition() {
		return new ConditionImpl();
	}
	
	private class ConditionImpl implements Condition {
		Object condition = new Object();
		
		@Override
		public void await() throws InterruptedException {
			unlock();
			
			synchronized (condition) {
				condition.wait();
			}
			
			lock();
		}

		@Override
		public void signal() {
			synchronized (mutex) {
				if (owner != Thread.currentThread()) // 
					throw new IllegalMonitorStateException("signal(): owner != currentThread");
			}
			
			synchronized (condition) {
				condition.notify();
			}
		}

		@Override
		public void signalAll() {
			synchronized (mutex) {
				if (owner != Thread.currentThread())
					throw new IllegalMonitorStateException("signal(): owner != currentThread");
			}
			
			synchronized (condition) {
				condition.notifyAll();
			}
		}
		
	}

}
