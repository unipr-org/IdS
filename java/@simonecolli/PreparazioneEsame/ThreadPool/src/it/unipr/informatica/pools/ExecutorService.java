package it.unipr.informatica.pools;

import it.unipr.informatica.pools.executors.*;

public interface ExecutorService extends Executor{
	public void shutdown();
	
//	gestione con future
	public Future<?> submit(Runnable task);// solo eccezione
	public <RetType> Future<RetType> submit(Callable<RetType> task); // eccezione e valore
	
//	gestisce con callback
	public void submit(Runnable task, Callback<?> callback); // solo eccezzione
	public <RetType> void submit(Callable<RetType> task, Callback<RetType> callback); // eccezione e valore
}
