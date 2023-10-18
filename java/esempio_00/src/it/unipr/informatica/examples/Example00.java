package it.unipr.informatica.examples;

public class Example00 {
	private void go() {
		for(int j = 0; j < 5; ++j) {
			Thread a = new ThreadA(j);
			a.start();
			
			Thread b = new Thread(new ThreadB(j));
			b.start();
			
			Thread c = new ThreadC(j);
			c.start();
			
			Thread d = new Thread(new ThreadD(j));
			d.start();
			
			
			// Classi interne anonime sono classi interne private solo che possiamo non dargli un nome
			// Non hanno costruttori
			final int idE = j;
			
			Thread e = new Thread() {
				// Questo e' il corpo della classe anonima
				// E' una sottoclasse di Thread di cui non conosco il nome
				@Override
				public void run() {
					// `j` cambia ogni iterazione, quindi la classe non sa quale usare
					// Devo usare una variabile final
					print('E', idE);
				}
			};
			e.start();
			
			
			final int idF = j;
			Thread f = new Thread(new Runnable() {
				@Override
				public void run() {
					print('F', idF);
					
					// `this` si riferisce a `Thread`
					// `Example00.this` si riferisce all'oggetto `this` prima che 
					// venisse chiamata la `new`
				}
			});
			f.start();

			
			// Interfaccia funzionale: interfaccia con un solo metodo
			// Sfrutto queste "lambda" per scrivere solo quello che mi serve
			//
			// In questo caso il compilatore capisce che sto implementando 
			// l'interfaccia `runnable`
			//
			// Una lambda exp. e' un oggetto
			final int idG = j;
			Thread g = new Thread(() -> {
				print('G', idG);
			});
			g.start();
			
			
			// Questa serve per funzioni che ritornano un risultato
			final int idH = j;
			Thread h = new Thread(() -> print('H', idH));
			h.start();
			
		}
	}
	
	private void print(char letter, int id) {
		for(int i = 0; i < 10; ++i)
			System.out.println(letter + ": " + i + " - id: " + id);
	}
	
	public static void main(String[] args) {
		new Example00().go();
	}
	
	// Inner Class
	// Una classe interna statica non ha accesso diretto ai membri 
	// non statici della classe esterna, ma può accedere solo a membri 
	// statici della classe esterna, se presenti.
	//
	// Se dichiaro le classi interne come statiche, posso istanziarle 
	// direttamente all'interno del metodo `go` o `main` senza dover 
	// istanziare un oggetto della classe esterna. 
	private class ThreadC extends Thread {
		private int id;
		
		private ThreadC(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			print('C', id);
		}
	}
	
	// Le classi interne private sono considerate "sporche"
	private class ThreadD implements Runnable {
		private int id;
		
		private ThreadD(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			print('D', id);
		}
	}
}
