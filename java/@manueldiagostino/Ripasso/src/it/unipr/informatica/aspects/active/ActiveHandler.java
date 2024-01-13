package it.unipr.informatica.aspects.active;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface ActiveHandler<T extends Active<?>> {
	public T get();
	public void shutdown();
}
