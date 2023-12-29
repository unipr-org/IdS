package exam_22_09_14.abstracts;

public interface Resource {
	public boolean resHasOwner();
	public void acquire();
	public void release();
}
