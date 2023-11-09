package it.unipr.informatica.esempi;


// thread che conta da 1 a 10 poi muore


public class Example00Alternative {

	private void start() {
//		System.out.println("prova avvio");

		for(int j = 0; j < 5; ++j) {
//			Thread a = new ThreadA(j);
//			a.start();
//			
//			Thread b = new Thread(new ThreadB(j));
//			b.start();
//			
//			Thread c = new ThreadC(j);
//			c.start();
//			 
//			Thread d = new Thread(new ThreadD(j));
//			d.start();
			
//			essendo final non è variabile quindi e non ad errore
			// si portrebbe scrivere anche int idE = j perchè il compilatore java riconosce che idE non varia
//			final int idE = j; 
////			classe anonima
//			Thread e = new Thread() {
//				
//				@Override
//				public void run() {
////					j da errore perché è mutabile
////					print('E', j);
//					print('E', idE);
//				}
//			};
//			e.run();
			
			
//			final int idF = j; // avrei potuto usare anche idE
//			Thread f = new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
////					
//					print('F', idF);
//				}
//			});
//			f.run();
			
			
//			Lamda expressions
			
//			Runnable è un interfaccia funzionale (interfaccia con un solo metodo)
			final int idG = j; // avrei potuto usare anche idE
			
//			in questo caso costruisce una inner class di Runnable
			Thread g = new Thread(() -> {
				print('G', idG);
			});
			g.run();
			
			
			final int idH = j;
			Thread h = new Thread(() -> print('H', idH));
			h.run();

		}
	}
	
	public static void main(String[] args) {
		new Example00Alternative().start();
		
	}
	
	private void print(char letter, int id) {
		for(int i = 0; i < 10; ++i)
			System.out.println(letter + " (" + id + "): " + i);
	}
	
//	le inner class possono accedere ai features (metodi ed attributi) dell'istanza dell
//	classe che le contiene
	
	private class ThreadC extends Thread{
		
		private int id_;
		
		private ThreadC(int id) {
			
//			test di validita' delle precondizioni
//			assert(id < 0); o if
			
//			sono classi interne quindi solo noi le usiamo
//			if(id < 0)
//				throw new IllegalArgumentException("Id negativo");

			id_ = id;
		}
		@Override
		public void run() {
//			Example00.this fa riferimento all'istanza che ha costruito questo metodo
				print('C', id_);
		}
	}
	private class ThreadD implements Runnable{
		
		private int id_;
		public ThreadD(int id) {
			
//			test di validita' delle precondizioni
//			assert(id < 0); o if
			
//			if(id < 0)
//				throw new IllegalArgumentException("Id negativo");

			id_ = id;
		}
		@Override
		public void run() {
			print('D', id_);
		}
	}
	
}
