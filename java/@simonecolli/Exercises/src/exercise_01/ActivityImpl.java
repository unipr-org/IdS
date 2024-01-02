package exercise_01;

public class ActivityImpl implements Activity {

	private Object mutex_;
	
	public ActivityImpl() {
		mutex_ = new Object();
	}
	@Override
	public Thread perform(Object o) {
		Runnable runnable = () -> {
			synchronized (mutex_) {
				print(o);
			}
		};
		return new Thread(runnable);
	}
	
	private void print(Object o) {
		System.out.println(o.toString());
	}

}
