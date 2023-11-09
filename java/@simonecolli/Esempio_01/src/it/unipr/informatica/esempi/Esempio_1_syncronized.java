package it.unipr.informatica.esempi;

public class Esempio_1_syncronized {
	
	private volatile boolean flag_ = false;
	
	public static void main(String[] args) {
		new Esempio_1_syncronized().go();
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
				
//				posso farlo perché sulla creazione il notifier si porta dietro il
//				this di chi lo ha creato
				synchronized (Esempio_1_syncronized.this) {
					flag_ = true;					
				}
				
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
			
			boolean syncDone;
			do {
				synchronized (Esempio_1_syncronized.this) {
					syncDone = flag_;					
				}
				
			} while(!syncDone);
			
			System.out.println("Waiter terminated");
		}
	}
}
