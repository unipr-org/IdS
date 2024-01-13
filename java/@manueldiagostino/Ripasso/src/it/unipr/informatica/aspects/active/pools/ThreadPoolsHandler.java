package it.unipr.informatica.aspects.active.pools;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ThreadPoolsHandler {
	private static volatile ThreadPoolsHandler instance;
	
	private ThreadPoolsHandler() {
	}
	
	public static ThreadPoolsHandler getInstance() {
		if (instance==null) {
			synchronized (ThreadPoolsHandler.class) {
				if (instance==null)
					instance = new ThreadPoolsHandler();
			}
		}
		
		return instance;
	}
	
	public static ThreadPool getNewThreadPool() {
		return new SimpleThreadPool();
	}
	
	public static ThreadPool getNewThreadPool(int size) {
		return new SimpleThreadPool(size);
	}
	
}
