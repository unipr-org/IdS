package it.unipr.informatica.concurrent.locks;

public class ReentrantLock implements Lock {
	private Thread owner;
	private Object mutex;
	private int counter; // Ci serve perche' e' un lock rientrante
	/*
	 * un lock rientrante è un tipo di lock che consente a un thread di acquisire 
	 * più volte lo stesso lock senza causare un deadlock. In altre parole, 
	 * se un thread ha già acquisito un lock, può acquisirlo nuovamente senza problemi.
	 */	

	public ReentrantLock() {
		this.owner = null;
		this.mutex = new Object();
		this.counter = 0;
	}
	
	@Override
	public void lock() {
		Thread currentThread = Thread.currentThread();
		
		synchronized (mutex) {
			if(counter < 0)
				throw new IllegalMonitorStateException("counter < 0"); // Monitor = mutex
			
			while(owner != null && owner != currentThread) {
				// Se il mutex e' attualmente posseduto da un altro thread, attendo
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					throw new IllegalMonitorStateException("interrupted");
				}
			}
			
			if(owner == null)
				owner = currentThread;
			
			++counter; 
			// Serve per indicare quante volte questo lock viene acquisito da un thread
		}
	}
	
	@Override
	public void unlock() {
		synchronized (mutex) {
			if(owner != Thread.currentThread())
				throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			
			if(counter <= 0)
				throw new IllegalMonitorStateException("counter <= 0");
			
			--counter;
			
			if(counter == 0) {
				owner = null;
				mutex.notify();
				// In questo caso il thread ha finito e notifica gli altri in attesa
			}
		}
	}
	
	@Override
	public Condition newCondition() {
		return new InnerCondition();
	}
	
	private class InnerCondition implements Condition {
		private Object condition;
		
		private InnerCondition() {
			this.condition = new Object();
		}
		
		@Override
		public void await() throws InterruptedException {
			unlock(); 
			// Effettua il rilascio del lock da cui e' stata creata la condizione,
			// in quanto non riesce a proseguire il suo flusso di esecuzione dato 
			// che non sta aspettando il verificarsi della condizione
			
			synchronized (condition) {
				// Si mette in attesa che la condizione venga segnalata
				condition.wait();
			}
			
			lock(); // Riacquisisce il lock
		}
		
		@Override
		public void signal() {
			synchronized (mutex) {
				if(owner != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}
			
			synchronized (condition) {
				// Se la condizione si verifica, lo notifico
				condition.notify();
			}
		}
		
		@Override
		public void signalAll() {
			synchronized (mutex) {
				if(owner != Thread.currentThread())
					throw new IllegalMonitorStateException("owner != Thread.currentThread()");
			}
			
			synchronized (condition) {
				condition.notifyAll();
			}
		}
	} // ! InnerCondition

} // ! ReentrantLock
