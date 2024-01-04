package exam_2023_02_07.lab.concrete;

import exam_2023_02_07.lab.abstracts.Resource;
import exam_2023_02_07.lab.dispatcher.ResourceDispatcher;

public class ResourceImpl implements Resource{

	private int id_;
	private Thread owner_;
	private Object mutex_;
	
	public ResourceImpl(int id) {
		this.id_ = id;
		this.owner_ = null;
		this.mutex_ = new Object();
	}

	@Override
	public int getID() {
		return id_;
	}

	@Override
	public void acquire() throws InterruptedException {
		owner_ = Thread.currentThread();
	}

	@Override
	public void release() {
		owner_ = null;
		ResourceDispatcher.getInstance().addResource(this);
	}

	@Override
	public int use() {
		Thread curret = Thread.currentThread();
		synchronized (mutex_) {
			if(owner_ != curret)
				throw new IllegalMonitorStateException("owner_ != curret");
			
			return (int) (Math.random() * 10);
		}
	}

}
