package it.unipr.informatica.esempi;

public class ThreadB implements Runnable{
	
	private int id_;
	public ThreadB(int id) {
		
//		test di validita' delle precondizioni
//		assert(id < 0); o if
		
		if(id < 0)
			throw new IllegalArgumentException("Id negativo");

		id_ = id;
	}
	@Override
	public void run() {
		for(int i = 0; i < 10; ++i) {
			System.out.println("B (" + id_ + "): " + i);
		}
	}
}
