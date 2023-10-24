package it.unipr.informatica.exercise;

public class ActivityImpl implements Activity {
	private Object mutex = new Object();
	
	@Override
	public Thread perform(Object o) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				synchronized (mutex) {
					print(o);
				}
			}
		};
		
		return new Thread(runnable); 
	}
	
	private void print(Object o) {
		if(o == null)
			System.out.println("Null");
		else
			System.out.println(o.toString());
	}
}
