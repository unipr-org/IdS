package it.unipr.informatica.exercise;

public class ExecutorImpl implements Executor {
	@Override
	public void launch(Task[] tasks) {
		if(tasks == null)
			throw new IllegalArgumentException("tasks");
		
		Object startedMutex = new Object();
		
		Counter counter = new Counter();
		counter.value = tasks.length;
		
		Object[] mutexes = new Object[tasks.length];
		
		System.out.println("Thread main avviato");
		
		synchronized (startedMutex) { // Acquisisco il mutex principale
			for (int i = 0; i < tasks.length; ++i) {
				
				int id = i;
				mutexes[id] = new Object(); // Per ogni task creo un mutex

				// Creo interfaccia runnable per eseguire un thread che esegue la task
				Runnable runnable = () -> { 
					// In questo caso il thread, dopo essere stato eseguito, 
					// entra subito in questa sezione critica perche' il mutex
					// della task e' libero
					System.out.println("Thread " + id + " avviato");
					
					System.out.println("Thread " + id + " attende mutexes[" + id + "]");
					
					synchronized (mutexes[id]) {
						
						System.out.println("Thread " + id + " acquisisce mutexes[" + id + "]");
						
						new Thread(() -> {
							System.out.println("Thread figlio " + id + " avviato");
							tasks[id].run(mutexes[id]);
							System.out.println("Thread figlio " + id + " terminato");
						}).start(); // Eseguo la task

						try {
							System.out.println("Thread " + id + " attende evento[inizio Task " + id + "] e rilascia mutexes[" + id + "]");
							mutexes[id].wait();
							System.out.println("Thread " + id + " risvegliato da evento[termine Task " + id + "] e riacquisisce mutexes[" + id + "]");
							// Il thread principale si mette in attesa del thread
							// figlio che inizia ad eseguire la task
						} catch (InterruptedException e) {
							return;
						}
						
						// Quando viene risvegliato dal thread figlio si mette in attesa
						// del mutex principale per decrementare la variabile count
						System.out.println("Thread " + id + " attende startedMutex");
						synchronized (startedMutex) {
							System.out.println("Thread " + id + " acquisisce startedMutex");
							
							counter.value--;
							System.out.println("Thread " + id + " decrementa counter.value [old-value=" + (counter.value + 1) + ", new-value="+ (counter.value) + "]");
							
							startedMutex.notifyAll();
							System.out.println("Thread " + id + " notifica gli altri thread in attesa su startedMutex");
							System.out.println("Thread " + id + " rilascia startedMutex");
						}
					}
					System.out.println("Thread " + id + " terminato");
				};

				new Thread(runnable).start();
			}

			while (counter.value > 0) {
				try {
					System.out.println("Thread main rilascia e attende evento su startedMutex [counter.value=" + counter.value + "]");
					// Il thread principale si mette in attesa che i thread figli decrementino 
					// il mutex principale
					startedMutex.wait();
					System.out.println("Thread main notificato su startedMutex [counter.value=" + counter.value + "]");
				} catch (InterruptedException e) {
					return;
				}
				// Quando il thread principale esce da questo ciclo vuol dire che tutti i figli 
				// hanno completato le proprie task
			}
			System.out.println("Thread main terminato");
		}
	}
	
	private class Counter {
		private int value;
	}
}
