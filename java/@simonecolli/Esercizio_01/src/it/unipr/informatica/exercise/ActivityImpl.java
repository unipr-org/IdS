package it.unipr.informatica.exercise;

public class ActivityImpl implements Activity{

	
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

//	i metodi privati sempre in fondo
	private void print(Object o) {
		if (o == null)
			System.out.println("Oggetto nullo");
		else
			System.out.println(o.toString());
		
//		si protrebbe fare anche System.out.println(o) perché fa già il controllo di null
	}
}
