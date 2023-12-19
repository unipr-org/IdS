package it.unipr.informatica.pools;

public class Executors{
	public static ExecutorService newThreadPool() {
		return new SimpleThreadPool();
	}
	public static ExecutorService newThreadPool(int workersNum) {
		return new SimpleThreadPool(workersNum);
	}
}
