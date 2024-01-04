package exam_2023_02_07.lab.concurrency.abstracts;

public interface Lock {
	public void lock() throws InterruptedException;
	public void unlock();
	public Condition newCondition();
	
}
