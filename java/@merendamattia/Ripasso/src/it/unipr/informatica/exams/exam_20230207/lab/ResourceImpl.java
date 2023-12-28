package it.unipr.informatica.exams.exam_20230207.lab;

public class ResourceImpl implements Resource {
	private int ID;
	private Object mutex;
	private boolean isFree;
	
	public ResourceImpl(int ID) {
		this.ID = ID;
		this.mutex = new Object();
		this.isFree = true;
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void acquire() throws InterruptedException {
		synchronized (mutex) {
			while(!isFree)
				mutex.wait();
			isFree = false;
		}
	}

	@Override
	public void release() {
		synchronized (mutex) {
			isFree = true;
			mutex.notify();
		}
		
	}

	@Override
	public int use() {
		return (int) (Math.random() * 10);
	}

}
