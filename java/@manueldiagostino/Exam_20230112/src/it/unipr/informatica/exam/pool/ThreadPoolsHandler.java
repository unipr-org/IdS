package it.unipr.informatica.exam.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 * 
 * Gestisce la concessione di pools di thread
 */
public class ThreadPoolsHandler {
	private static volatile SimpleThreadPool instance;
	
	private ThreadPoolsHandler() {
		instance = null;
	}
	
	public static SimpleThreadPool newThreadPool() {
		if (instance == null) {
			synchronized (ThreadPoolsHandler.class) {
				if (instance == null)
					instance = new SimpleThreadPool();
			}
		}
		
		return instance;
	}
}
