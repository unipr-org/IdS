package exam_22_09_14.concurrency;

public interface Lock {
	public void lock() throws InterruptedException;
	public void unlock();
	public Condition newCondition();
}
