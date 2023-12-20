package it.unipr.informatica.pools;

import it.unipr.informatica.executors.*;

public interface ExecutorService extends Executor {
	public void shutdown();
	public void forceShutdown();
	
	public Future<?> submit(Runnable task); // Gestisco solo le eccezioni
	public <T> Future<T> submit(Callable<T> task); // Gestisco sia risultato che eccezioni
	
	public void submit(Runnable task, Callback<?> callback); // Gestisco solo le eccezioni
	public <T> void submit(Callable<T> task, Callback<T> callback); // Gestisco sia risultato che eccezioni
}
