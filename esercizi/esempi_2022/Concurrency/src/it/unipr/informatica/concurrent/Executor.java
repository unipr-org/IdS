/*
 * Executor
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface Executor {
	public void execute(Runnable command);
}
