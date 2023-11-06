package it.unipr.informatica.exercise;

public class Exercise03 {
	public static void main(String[] args) {
		// numTasks= 1 se si vuole capire il funzionamento 
		// numTasks= 10 esercizio completo 
		int numTasks = 4;
		
		Task[] tasks = new Task[numTasks];
		
		for(int i = 0; i < tasks.length; ++i) {
			int id = i;
			
			tasks[i] = (mutex) -> {
				if(mutex == null)
					throw new IllegalArgumentException("startedMutex");

				System.out.println("Thread figlio " + id + " - Task " + id + " iniziata");
				
				System.out.println("Thread figlio " + id + " attende mutexes[" + id + "]");
				
				
				synchronized (mutex) {
					System.out.println("Thread figlio " + id + " acquisisce mutexes[" + id + "]");
					mutex.notifyAll(); 
					// Vado a risvegliare il thread padre che aveva 
					// avviato questo thread
					System.out.println("Thread figlio " + id + " risveglia thread padre " + id);
					System.out.println("Thread figlio " + id + " rilascia mutexes[" + id + "]");
				}
				
				try {
					int time = (int) (500 + 1000 * Math.random());
					Thread.sleep(time);
				} catch (Exception e) {
					return;
				}
					
				System.out.println("Thread figlio " + id + " - Task " + id + " terminata");
			};
		}
		
		Executor executor = new ExecutorImpl();
		
		executor.launch(tasks);
		
		System.out.println("Started returned");
	}
}
