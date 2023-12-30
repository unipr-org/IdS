/*
 * ReloadableWorker
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.esempio16;

public interface ReloadableWorker extends Runnable {
	public int getVersion();
}
