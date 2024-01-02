package it.unipr.informatica.exams.exam_20230112.lab;

public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
