/*
 * Callable<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface Callable<T> {
	public T call() throws Exception;
}
