package exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts;

public interface BlockingQueue<T> {
	public T take() throws InterruptedException;
	public void put(T elem) throws InterruptedException;
	public boolean isEmpty();
}
