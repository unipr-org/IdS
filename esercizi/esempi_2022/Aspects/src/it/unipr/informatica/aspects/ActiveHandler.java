/*
 * ActiveHandler<T>
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

public interface ActiveHandler<T extends Active<?>> {
	public T get();

	public void shutdown();
}
