package it.unipr.informatica.exams.exam_20220114;

public class ResourceImpl implements Resource {
	private int ID;
	public boolean avaiable;
	private Object mutex;
	
	public ResourceImpl(int ID) {
		if(ID < 0)
			throw new IllegalArgumentException("ID < 0");
		this.ID = ID;
		this.avaiable = true;
		this.mutex = new Object();
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public int use() {
		synchronized (mutex) {
			return (int) (ID + Math.random() * 100);
		}	
	}

	@Override
	public void release() {
		synchronized (mutex) {
			avaiable = true;
		}	
	}
	
	public void acquire() {
		synchronized (mutex) {
			avaiable = false;
		}	
	}
	
	public boolean isFree() {
		synchronized (mutex) {
			return avaiable;
		}
	}

} // ! ResourceImpl
