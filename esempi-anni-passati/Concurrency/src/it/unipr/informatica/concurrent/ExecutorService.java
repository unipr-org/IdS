/*
 * ExecutorService
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface ExecutorService extends Executor {
	public void shutdown();

	public Future<?> submit(Runnable task);

	public void submit(Runnable task, Callback<?> callback);

	public <T> Future<T> submit(Callable<T> task);

	public <T> void submit(Callable<T> task, Callback<T> callback);
}
