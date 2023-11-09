package it.unipr.informatica.esempi;

public class Esempio_1_volatile {
	
	
	private volatile boolean flag_ = false;
	
	public static void main(String[] args) {
		new Esempio_1_volatile().go();
	}
	
	private void go() {
		new Waiter().start();

		new Notifier().start();
	}
	
	
	private class Notifier extends Thread{
		@Override
		public void run() {
			
			System.out.println("Notifier starter...");
			
			try {
				Thread.sleep(5000);
				
				flag_ = true;
				
				System.out.println("Notified");
					
			} catch (InterruptedException e) {
				System.out.println("Errore in Notifier -> run");
				e.printStackTrace();
				
				return;
			}

			System.out.println("Notifier terminated");
		}
	}
	
//	Esempio di attesa attiva -> busy waiting
	private class Waiter extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter started...");
			do {
				
//				System.out.println("About to sleep");
//				Thread.sleep(1000);
				
			} while(!flag_);
			
			System.out.println("Waiter terminated");
		}
	}
}
