package it.unipr.informatica.aspects.persistents;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface PersistentHandler<T extends Serializable> {
	public T get();
	public void commit() throws IOException;
	public void rollback() throws IOException;
	
}
