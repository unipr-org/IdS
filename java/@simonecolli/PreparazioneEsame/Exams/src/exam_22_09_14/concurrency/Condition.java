package exam_22_09_14.concurrency;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
