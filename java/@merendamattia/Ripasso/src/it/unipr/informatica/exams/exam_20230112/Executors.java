package it.unipr.informatica.exams.exam_20230112;

public class Executors {
	private static ExecutorService INSTANCE = null;
	
	public static ExecutorService getInstance() {
		if(INSTANCE == null) {
			synchronized (ExecutorService.class) {
				if(INSTANCE == null) {
					INSTANCE = new SimpleThreadPool(10);
				}
			}
		}
		return INSTANCE;
	}
	private Executors() {}
} // ! Executors
