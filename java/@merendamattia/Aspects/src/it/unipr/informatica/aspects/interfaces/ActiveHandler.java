/*
 * ActiveHandler<T>
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects.interfaces;

public interface ActiveHandler<T extends Active<?>> {
	public T get();

	public void shutdown();
}
