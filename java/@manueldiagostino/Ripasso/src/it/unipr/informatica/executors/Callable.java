package it.unipr.informatica.executors;

/**
 * 
 * @param <T> The parametric type
 */
public interface Callable<RetType> {
	public RetType call() throws Exception;
}
