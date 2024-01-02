package it.unipr.informatica.exercise.esercizio4;

public class MonitorImpl implements Monitor {
	private int ID;
	public boolean isDone;
	
	public MonitorImpl(int ID) {
		if(ID < 0)
			throw new IllegalArgumentException("ID < 0");
		this.ID = ID;
		this.isDone = false;
	}
	
	@Override
	public void await() throws InterruptedException {
		synchronized (this) {
			if(!isDone)
				this.wait();
			System.out.printf("[AWAIT] %s notificato - %s%n", this.toString(), Thread.currentThread());
		}
	}
	
	public void signal() {
		synchronized (this) {
			isDone = true;
			this.notifyAll();
			System.out.printf("[SIGNAL] %s notificato - %s%n", this.toString(), Thread.currentThread());
		}
	}
	
	public int getID() {
		return ID;
	}
	
	public boolean isDone() {
		synchronized (this) {
			return isDone;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MonitorImpl) {
			MonitorImpl other = (MonitorImpl) obj;
			return this.getID() == other.getID();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Monitor" + ID;
	}
} // ! MonitorImpl
