package it.unipr.informatica.aspects.active.pools;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ThreadPool extends Executor {
	public void shutdown();
	
	public void submit(Runnable runnable, Callback<?> callback);
	public <T> void submit(Callable<T> callable, Callback<T> callback);
	public Future<?> submit(Runnable runnable);
	public <T> Future<?> submit(Callable<T> callable);
}
