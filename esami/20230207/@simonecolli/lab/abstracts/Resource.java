package exam_2023_02_07.lab.abstracts;

public interface Resource {
	public int getID();
	public void acquire() throws InterruptedException;
	public void release();
	public int use();
}
