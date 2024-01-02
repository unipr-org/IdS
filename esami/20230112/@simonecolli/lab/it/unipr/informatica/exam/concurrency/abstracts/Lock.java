package exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts;

public interface Lock {
	public void lock() throws InterruptedException;
	public void unlock();
	public Condition newCondition();
}
