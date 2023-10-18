/*
 * WorkerDelegate
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

public class WorkerDelegate {
	public void work(ReloadableWorker worker) {
		try {
			String name = Thread.currentThread().getName();

			System.out.println("Worker " + name + " in version " + worker.getVersion() + " started");

			Thread.sleep((int) (5000 + 3000 * Math.random()));

			System.out.println("Worker " + name + " in version " + worker.getVersion() + " terminated");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}
}
