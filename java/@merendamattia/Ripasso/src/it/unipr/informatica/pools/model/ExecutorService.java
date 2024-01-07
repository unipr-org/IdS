package it.unipr.informatica.pools.model;

import it.unipr.informatica.executors.*;
import it.unipr.informatica.executors.model.Callable;
import it.unipr.informatica.executors.model.Callback;
import it.unipr.informatica.executors.model.Future;

public interface ExecutorService extends Executor {
	public void shutdown();
	public void forceShutdown();
	
	public Future<?> submit(Runnable task); // Gestisco solo le eccezioni
	public <T> Future<T> submit(Callable<T> task); // Gestisco sia risultato che eccezioni
	
	public void submit(Runnable task, Callback<?> callback); // Gestisco solo le eccezioni
	public <T> void submit(Callable<T> task, Callback<T> callback); // Gestisco sia risultato che eccezioni
}
