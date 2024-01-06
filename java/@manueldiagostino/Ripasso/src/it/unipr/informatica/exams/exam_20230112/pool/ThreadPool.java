package it.unipr.informatica.exams.exam_20230112.pool;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface ThreadPool {
	public void execute(Runnable runnable);

	public void shutdown();
}
