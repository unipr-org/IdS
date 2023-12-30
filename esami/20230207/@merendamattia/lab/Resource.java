package it.unipr.informatica.exams.exam_20230207.lab;

public interface Resource {
	public int getID();
	public void acquire() throws InterruptedException;
	public void release();
	public int use();
}
