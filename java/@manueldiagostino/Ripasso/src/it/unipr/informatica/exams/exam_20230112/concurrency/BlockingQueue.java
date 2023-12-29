package it.unipr.informatica.exams.exam_20230112.concurrency;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface BlockingQueue<T> {
	public void put(T elem) throws InterruptedException;
	public T take() throws InterruptedException;
	public boolean isEmpty();
	public boolean isFull();
	public int remainingCapacity();
	public int size();
}
