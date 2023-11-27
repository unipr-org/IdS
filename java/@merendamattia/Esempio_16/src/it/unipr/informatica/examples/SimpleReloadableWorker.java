/*
 * SimpleReloadableWorker
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

public class SimpleReloadableWorker implements ReloadableWorker {
	private WorkerDelegate delegate = new WorkerDelegate();

	private int version = 1;

	@Override
	public void run() {
		delegate.work(this);
	}

	@Override
	public int getVersion() {
		return version;
	}
}
