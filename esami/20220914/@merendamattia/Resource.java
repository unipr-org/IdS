package it.unipr.informatica.exams.exam_20220914;

public class Resource {
	private int ID;
	private Object mutex;
	private boolean available;
	
	public Resource(int ID) {
		if(ID < 0)
			throw new IllegalArgumentException("ID < 0");
		this.ID = ID;
		this.mutex = new Object();
		this.available = true;
	}
	
	public boolean isFree() { 
		synchronized (mutex) {
			return available;			
		}
	}
	
	public void acquire() {
		synchronized (mutex) {
			available = false;			
		}
	}
	
	public void release() {
		synchronized (mutex) {
			available = true;			
		}
	}
	
} // ! Resource
