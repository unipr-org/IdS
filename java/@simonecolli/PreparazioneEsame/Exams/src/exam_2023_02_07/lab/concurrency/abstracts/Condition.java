package exam_2023_02_07.lab.concurrency.abstracts;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
	
}
