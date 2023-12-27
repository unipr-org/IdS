package it.unipr.informatica.exam.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ThreadPool {
	public void execute(Runnable runnable);
	public void shutdown();
}
