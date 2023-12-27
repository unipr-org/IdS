package it.unipr.informatica.exams.exam_20230112;

public interface BlockingQueue<T> {
	public T take() throws InterruptedException;
	public void put(T item) throws InterruptedException;
	public boolean isEmpty();
}
