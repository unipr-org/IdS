package it.unipr.informatica.examples.esempio1;

public class Example01 {
	private volatile boolean doneA = false;
	// Per come lo abbiamo scritto `volatile`, anche doneB "eredita" il `volatile`,
	// perche' l'oggetto Example01 viene caricato in memoria
	
	private boolean doneB = false;
	private boolean doneC = false;
	
	private void go() {
//		new WaiterA().start();
//		new NotifierA().start();
//		
//		new WaiterB().start();
//		new NotifierB().start();
		
		new WaiterC().start();
		new NotifierC().start();
	}
	
	public static void main(String[] args) {
		new Example01().go();
	}
	
	private class NotifierA extends Thread {
		@Override
		public void run() {
			System.out.println("Notifier A started");
			
			try {
				
				Thread.sleep(5000);
					
				doneA = true;
					
				System.out.println("Notified A");
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				// Devo SEMPRE gestire le eccezioni
				return;
			}

			System.out.println("Notifier A terminated");
		}
	}
	
	private class WaiterA extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter A started");
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
			} while(!doneA);

			System.out.println("Waiter A terminated");
		}
	}
	
	private class NotifierB extends Thread {
		@Override
		public void run() {
			System.out.println("Notifier B started");
			
			try {
				
				Thread.sleep(5000);
				
				synchronized (Example01.this) {
					// Prendo l'oggetto che esisteva quando sono stati creati NotifierB e WaiterB
					doneB = true;
				}
					
				System.out.println("Notified B");
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				return;
			}

			System.out.println("Notifier B terminated");
		}
	}
	
	private class WaiterB extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter B started");
		
			boolean done;
			
			do {
				synchronized (Example01.this) {
					done = doneB;
					// Viene copiato perche' e' un dato primitivo
				}
			} while(!done);

			System.out.println("Waiter B terminated");
		}
	}
	
	private class NotifierC extends Thread {
		@Override
		public void run() {
			System.out.println("Notifier C started");
			
			try {
				
				Thread.sleep(5000);
				
				synchronized (Example01.this) {
					doneC = true;
					Example01.this.notifyAll();
					// Se inverto queste due istruzioni nella sezione critica, non succede nulla
					// perche' dentro la sezione critica ci sta un solo thread alla volta.
					// Viceversa, se non siamo nella sezione critica, puo' provocare numerosi problemi.
				}
					
				System.out.println("Notified C");
			} catch (InterruptedException e) {
				System.out.println("Interrupted C");
				return;
			}

			System.out.println("Notifier C terminated");
		}
	}
	
	private class WaiterC extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter C started");

			try {
				Thread.sleep(8000);
				
				synchronized (Example01.this) {
					// Se non metto questo controllo si perde il notifier (se tengo `Thread.sleep(8000);`
					if(!doneC)
						Example01.this.wait();
				}
			} catch (Exception e) {
				System.out.println("Interrupted C");
				return;
			}

			System.out.println("Waiter C terminated");
		}
	}
}
