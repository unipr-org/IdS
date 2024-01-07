package it.unipr.informatica.pools;

import it.unipr.informatica.pools.model.ExecutorService;

public class Executors {
	public static ExecutorService newThreadPool(int workers) {
		return new SimpleThreadPool(workers);
	}
	public static ExecutorService newThreadPool() {
		return new SimpleThreadPool();
	}
	
	private Executors() { }
}
