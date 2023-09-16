/*
 * Example01
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

public class Example01 {
	private Object mutex = new Object();

	private boolean waiting = false;

	private void go() {
		waiting = false;

		Thread notifier = new Thread(this::doNotify);

		Thread waiter = new Thread(this::doWait);

		notifier.start();

		waiter.start();
	}

	private void doWait() {
		System.out.println("Waiter started");

		synchronized (mutex) {
			waiting = true;

			mutex.notifyAll();

			try {
				mutex.wait();
			} catch (Throwable throwable) {
				// Blank
			}

			waiting = false;
		}

		System.out.println("Waiter terminated");
	}

	private void doNotify() {
		System.out.println("Notifier started");

		synchronized (mutex) {
			try {
				while (!waiting)
					mutex.wait();

				Thread.sleep(5000);

				mutex.notifyAll();
			} catch (Throwable throwable) {
				// Blank
			}
		}

		System.out.println("Notifier terminanted");
	}

	public static void main(String[] args) {
		new Example01().go();
	}
}
