package exam_2023_02_07.lab.it.unipr.informatica.exam.abstracts;

public interface Resource {
	public int getID();
	public void acquire() throws InterruptedException;
	public void release();
	public int use();
}
