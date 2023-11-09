package it.unipr.informatica.esempi;

public class Esempio_01 {
	
	private boolean flag_ = false;
	
	public static void main(String[] args) {
		new Esempio_01().go();
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
			
			try {
				do {
				
					System.out.println("About to sleep");
					Thread.sleep(1000);
					
				} while(!flag_);
			} catch (InterruptedException e) {
				System.out.println("Errore in Waiter -> run");
				e.printStackTrace();
				return;
			}
			
			System.out.println("Waiter terminated");
		}
	}
}
