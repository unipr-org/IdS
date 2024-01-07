package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Callable<ResultType> {
	public ResultType call() throws Exception;
}
