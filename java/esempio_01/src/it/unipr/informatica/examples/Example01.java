package it.unipr.informatica.examples;

public class Example01 {
	private volatile boolean done = false;
	
	private void go() {
		new Waiter().start();
		new Notifier().start();
	}
	
	public static void main(String[] args) {
		new Example01().go();
	}
	
	private class Notifier extends Thread {
		@Override
		public void run() {
			System.out.println("Notifier started");
			
			try {
				
				Thread.sleep(5000);
					
				done = true;
					
				System.out.println("Notified");
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				// Devo SEMPRE gestire le eccezioni
				return;
			}

			System.out.println("Notifier terminated");
		}
	}
	
	private class Waiter extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter started");
			// Attesa attiva (busy waiting) perche' aspetto e guardo, aspetto e guardo, aspetto e guardo (in loop)
		
			do {
//				try {
//					System.out.println("About to sleep");
//					
//					Thread.sleep(1000);
//				
//				} catch (InterruptedException e) {
//					System.out.println("Interrupted");
//					// Devo SEMPRE gestire le eccezioni
//					return;
//				}
				// System.out.println(done);
				// Se non metto nulla nel do-while, il Waiter non terminerà mai
				// Mi basta mettere una stampa (o semplicemente usare `volatile`) e si risolve il problema
				// Perchè questo? -> Java Memory Model
			} while(!done);

			System.out.println("Waiter terminated");
		}
	}
}
