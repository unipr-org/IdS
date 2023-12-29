package exam_2022_09_14.abstracts;

public interface Resource {
	public boolean resHasOwner();
	public void acquire();
	public void release();
}
