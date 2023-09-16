/*
 * Executors
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public class Executors {
	public static ExecutorService newFixedThreadPool(int count) {
		return new SimpleThreadPoolExecutorService(count);
	}

	private Executors() {
		// Blank
	}
}
