package it.unipr.informatica.aspects.active.pools;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Future<RetType> {
	public RetType get() throws Throwable;
}
