/*
 * Lock
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent.locks;

public interface Lock {
	public void lock();

	public void unlock();

	public Condition newCondition();
}
