package it.unipr.informatica.exams.exam_20230207.lab;

public interface BlockingQueue<T> {
	public T take();
	public void put(T item);
	public int remainingCapacity();
	public boolean isEmpty();
	public int size();
}
