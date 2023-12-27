package it.unipr.informatica.exams.exam_20230112;

public interface Lock {
	public void lock();
	public void unlock();
	public Condition newCondition();
}
