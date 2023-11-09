package it.unipr.informatica.esempi;

public class Esempio_1_wait_notify {
	
	
	private volatile boolean flag_ = false;
	
	public static void main(String[] args) {
		new Esempio_1_wait_notify().go();
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
//				se la sleep non ci fosse o altri problemi che conportano
//				la partenza di notify prima di wait comparta un wait che 
//				rimane sempre in attesa
				Thread.sleep(5000);
				
//				una sleep dentro una sezione critica è un errore non banale
				
				
				synchronized (Esempio_1_wait_notify.this) {
//					flag_ = true; può essere sia prima che dopo la notifyAll
//					perché fa parte della sezione pritica, metterlo solo è
//					solo cattivo stile
					flag_ = true;
					Esempio_1_wait_notify.this.notifyAll();
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
	
	private class Waiter extends Thread {
		@Override
		public void run() {
			System.out.println("Waiter started...");
			try  {
//				entro nella sezione critica
				synchronized (Esempio_1_wait_notify.this) {
//					libera la sezione critica
					if(!flag_)
						Esempio_1_wait_notify.this.wait();
//					torno nella sezione critica
//					......
				}
			} catch (Exception e) {
				System.out.println("Errore in Waiter -> run");
				e.printStackTrace();
			}
			
			System.out.println("Waiter terminated");
		}
	}
}
