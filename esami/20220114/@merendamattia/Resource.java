package it.unipr.informatica.exams.exam_20220114;

public interface Resource {
	public int getID();
	public int use();
	public void release();
}
