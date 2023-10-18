/*
 * Example03
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import it.unipr.informatica.concurrent.ArrayBlockingQueue;
import it.unipr.informatica.concurrent.BlockingQueue;

public class Example03 {
	private void go() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

		for (int i = 0; i < 5; ++i) {
			Consumer consumer = new Consumer(i, queue);

			new Thread(consumer).start();
		}

		for (int i = 0; i < 5; ++i) {
			Producer producer = new Producer(i, queue);

			new Thread(producer).start();
		}
	}

	public static void main(String[] args) {
		new Example03().go();
	}
}
