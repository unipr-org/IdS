package exam_22_09_14.concretes;

import exam_22_09_14.abstracts.Resource;

public class ResourceImpl implements Resource {
	
	private boolean hasOwner;
	private int id_;
	public ResourceImpl(int id) {
		hasOwner = false; // no owner
		id_ = id;
	}
	
	@Override
	public boolean resHasOwner() {
		return hasOwner;
	}

	@Override
	public void acquire() {
		if(hasOwner)
			throw new IllegalAccessError("hasOwner");
		
		hasOwner = true;
		System.out.println("[" + id_ + " acquired]");
	}

	@Override
	public void release() {
		if(!hasOwner)
			throw new IllegalMonitorStateException("!hasOwner");
		
		System.out.println("[" + id_ + " release]");

		hasOwner = false;
	}

}
