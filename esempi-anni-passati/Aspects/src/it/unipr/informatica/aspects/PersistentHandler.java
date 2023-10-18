/*
 * PersistentHandler<T>
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.io.IOException;
import java.io.Serializable;

public interface PersistentHandler<T extends Serializable> {
	public T get();

	public void rollback() throws IOException;

	public void commit() throws IOException;
}
