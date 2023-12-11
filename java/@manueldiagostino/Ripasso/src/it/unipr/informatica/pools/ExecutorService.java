package it.unipr.informatica.pools;

import it.unipr.informatica.executors.Callable;
import it.unipr.informatica.executors.Callback;
import it.unipr.informatica.executors.Future;

public interface ExecutorService extends Executor {
	public void shutdown();
	
	public 
		Future<Void> submit(Runnable runnable);
	public
		void submit(Runnable runnable, Callback<?> callback);
	public <T>
		Future<T> submit(Callable<T> callable);
	public <T>
		void submit(Callable<T> callable, Callback<T> callback);
}
