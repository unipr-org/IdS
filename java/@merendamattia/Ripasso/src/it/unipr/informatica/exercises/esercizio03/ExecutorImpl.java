package it.unipr.informatica.exercises.esercizio03;

public class ExecutorImpl implements Executor {
	private Object isPreparationTerminated;
	private int numberOfTasks;
	private int numberOfPreparationTerminated;
	
	public ExecutorImpl() {
		this.isPreparationTerminated = new Object();
		this.numberOfTasks = 0;
	}
	
	@Override
	public void launch(Task[] tasks) {
		numberOfTasks = tasks.length;
		
		try {
			for(int i = 0; i < numberOfTasks; ++i) {
				int c = i;
				
				new Thread(() -> {
					tasks[c].run(isPreparationTerminated);
				}).start();
			}
			
			numberOfPreparationTerminated = 0;
			
			while(numberOfPreparationTerminated < numberOfTasks) {
				
				synchronized (isPreparationTerminated) {
					isPreparationTerminated.wait();
				}
				
				++numberOfPreparationTerminated;	
			}
		
		} catch (InterruptedException e) {
			e.getCause();
		}
	}

} // ! ExecutorImpl
