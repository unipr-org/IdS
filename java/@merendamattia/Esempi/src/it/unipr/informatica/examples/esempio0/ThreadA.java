package it.unipr.informatica.examples.esempio0;

public class ThreadA extends Thread {
	private int id;
	
	public ThreadA(int id) {
		if(id < 0)
			throw new IllegalArgumentException("id");
		
		this.id = id;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; ++i)
			System.out.println("A: " + i + " - id: " + id);
	}
}
