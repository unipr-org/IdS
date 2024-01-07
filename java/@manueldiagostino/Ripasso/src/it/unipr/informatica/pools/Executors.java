package it.unipr.informatica.pools;

public class Executors {
	public static ExecutorService newThreadPool() {
		return new SimpleThreadPool();
	}

	private Executors() {
		// Blank
	}
}
