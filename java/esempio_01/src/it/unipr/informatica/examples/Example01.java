package it.unipr.informatica.examples;

public class Example01 {
	private boolean done = false;
	
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
		
			try {
				
				do {
					System.out.println("About to sleep");
					
					Thread.sleep(1000);
				} while(!done);
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				// Devo SEMPRE gestire le eccezioni
				return;
			}

			System.out.println("Waiter terminated");
		}
	}
}
