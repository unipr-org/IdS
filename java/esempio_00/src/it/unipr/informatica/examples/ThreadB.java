package it.unipr.informatica.examples;

public class ThreadB implements Runnable {
	private int id;
	
	public ThreadB(int id) {
		if(id < 0)
			throw new IllegalArgumentException("id");
		
		this.id = id;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; ++i)
			System.out.println("B: " + i + " - id: " + id);
	}
}
