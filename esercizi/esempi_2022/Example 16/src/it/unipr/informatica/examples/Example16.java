/*
 * Example16
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import it.unipr.informatica.aspects.ReloadableAspect;
import it.unipr.informatica.aspects.ReloadableHandler;

public class Example16 {
	private void go() {
		ReloadableHandler<Runnable> handler = ReloadableAspect.newHandler(Runnable.class,
				"it.unipr.informatica.examples.SimpleReloadableWorker", new String[] { "bin" });

		for (int i = 0; i < 10; ++i) {
			try {
				Runnable worker = handler.newInstance();

				new Thread(worker).start();

				Thread.sleep(5000);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Example16().go();
	}
}
