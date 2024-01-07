package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ExecutorService extends Executor {
	public void shutdown();
	public <T> void submit(Callable<T> callable, Callback<T> callback);
}
