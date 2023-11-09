package it.unipr.informatica.esempi;


// thread che conta da 1 a 10 poi muore


public class Example00 {

	public static void main(String[] args) {
//		System.out.println("prova avvio");

		for(int j = 0; j < 5; ++j) {
			Thread a = new ThreadA(j);
			a.start();
			
			Thread b = new Thread(new ThreadB(j));
			b.start();
			
//			questo genera un errore percé main è static quindi non è legato a nessun oggetto
//			per risolvere metto la inner class come static
			Thread c = new ThreadC(j);
			c.start();
			
			Thread d = new Thread(new ThreadD(j));
			d.start();
		}
		
	}
	
//	le inner class possono accedere ai features (metodi ed attributi) dell'istanza dell
//	classe che le contiene
	
	private static class ThreadC extends Thread{
		
		private int id_;
		
		private ThreadC(int id) {
			
//			test di validita' delle precondizioni
//			assert(id < 0); o if
			
			if(id < 0)
				throw new IllegalArgumentException("Id negativo");

			id_ = id;
		}
		@Override
		public void run() {
//			Example00.this fa riferimento all'istanza che ha costruito questo metodo
			for(int i = 0; i < 10; ++i) {
				System.out.println("C (" + id_ + "): " + i);
			}
		}
	}
	private static class ThreadD implements Runnable{
		
		private int id_;
		public ThreadD(int id) {
			
//			test di validita' delle precondizioni
//			assert(id < 0); o if
			
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

	
}
