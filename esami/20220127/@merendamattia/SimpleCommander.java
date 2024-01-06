package it.unipr.informatica.exams.exam_20220127;

import it.unipr.informatica.exams.exam_20220127.model.Commander;
import it.unipr.informatica.exams.exam_20220127.model.Slave;

public class SimpleCommander implements Commander {
	public static SimpleCommander INSTANCE = null;
	private SimpleCommander() { };
	public static SimpleCommander getInstance() {
		if(INSTANCE == null) {
			synchronized (SlaveManager.class) {
				if(INSTANCE == null)
					INSTANCE = new SimpleCommander();
			}
		}
		return INSTANCE;
	}

	@Override
	public boolean command(Slave slave) {
		Condition cond = new Condition();
		
		Thread exec = new Thread(() -> {
			slave.executePartB();
			cond.signal();
			slave.executePartC();
		});
		
		exec.start();
		
		slave.executePartA();
		cond.await();

		return slave.finish() == 0;
	}
	
	private class Condition {
		private Object mutex;
		private boolean signaled;
		
		public Condition() {
			this.mutex = new Object();
			this.signaled = false;
		}
		
		public void await() {
			synchronized (mutex) {
				try {
					while(!signaled) 
						mutex.wait();

				} catch (InterruptedException e) {
						System.err.println(e.getCause());
				}
			}
		}
		
		public void signal() {
			synchronized (mutex) {
				signaled = true;
				mutex.notify();
			}
		}
		
		public void signalAll() {
			synchronized (mutex) {
				signaled = true;
				mutex.notifyAll();
			}
		}
		
		
	} // ! Condition

} // ! SimpleCommander
