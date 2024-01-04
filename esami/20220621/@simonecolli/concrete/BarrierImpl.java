package exam_2022_06_22.concrete;

import java.util.Vector;

import exam_2022_06_22.abstracts.Barrier;

public class BarrierImpl implements Barrier {
	
	private static BarrierImpl INSTANCE = null;
	
	public static BarrierImpl getInstance() {
		if(INSTANCE == null)
			synchronized (BarrierImpl.class) {
				if(INSTANCE == null)
					INSTANCE = new BarrierImpl();
			}
		return INSTANCE;
	}

	private Object mutex_;
	private Vector<Object> objects_;
	private BarrierImpl() {
		mutex_ = new Object();
		objects_ = new Vector<>();
	}
	
	public Object getTarget() {
		return mutex_;
	}
	
	@Override
	public void add(Object o) {
		objects_.add(o);
	}

	@Override
	public void remove(Object o) {
		objects_.remove(o);
	}

	@Override
	public void await() throws InterruptedException {
		synchronized (mutex_) {
			mutex_.wait();
		}
	}

}
