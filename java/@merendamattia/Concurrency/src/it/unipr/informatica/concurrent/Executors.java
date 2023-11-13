package it.unipr.informatica.concurrent;

public class Executors {
	public static ExecutorService newFixedThreadPool(int count) {
		return new SimpleThreadPoolExecutorService(count);
	}
	
	private Executors() {
		// Vuoto
	}
}
