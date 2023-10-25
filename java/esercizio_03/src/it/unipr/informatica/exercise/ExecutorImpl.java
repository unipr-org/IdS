package it.unipr.informatica.exercise;

public class ExecutorImpl implements Executor {
	@Override
	public void launch(Task[] tasks) {
		if(tasks == null)
			throw new IllegalArgumentException("tasks");
		
		Object startedMutex = new Object();
		Counter counter = new Counter();
		
		counter.value = tasks.length;
		
		synchronized (startedMutex) {
			for(int i = 0; i < tasks.length; ++i) {
				int id = i;
				
				Runnable runnable = () -> {
					tasks[id].run(startedMutex);
				};
				
				new Thread(runnable).start();
			}
		
		
			while (counter.value != 0) {
				try {
					startedMutex.wait();
					
					counter.value--;
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
	
	private class Counter {
		private int value;
	}
}
