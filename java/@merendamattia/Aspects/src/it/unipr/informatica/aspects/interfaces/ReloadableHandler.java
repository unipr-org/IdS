/*
 * ReloadableHandler
 *
 * (c) 2022-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects.interfaces;

public interface ReloadableHandler<T> {
	public T newInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException;

	public Class<T> loadClass() throws ClassNotFoundException;
}
