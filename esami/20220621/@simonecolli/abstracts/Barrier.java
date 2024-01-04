package exam_2022_06_22.abstracts;

public interface Barrier {
	public void add(Object o);
	public void remove(Object o);
	public void await() throws InterruptedException;
}
