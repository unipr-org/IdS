package exam_2022_06_22.concrete;

import exam_2022_06_22.abstracts.Notifier;

public class NotifierImpl implements Notifier {
	private Thread cycle_;
	private Object mutex_;
	private boolean shutdown_;
	private Observer observer_;
	
	public NotifierImpl() {
		cycle_ = null;
		mutex_ = new Object();
		shutdown_ = false;
		observer_ = new Observer(BarrierImpl.getInstance().getTarget());
	}
	
	@Override
	public void start() {
		cycle_ = new Cycle();
		cycle_.start();
	}

	@Override
	public void stop() {
		synchronized (mutex_) {
			shutdown_ = true;
		}
	}
	
	@Override
	public Object getObject() {
		return observer_;
	}
	
	private class Cycle extends Thread {
		
		private Cycle() {
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (mutex_) {
					if(shutdown_)
						break;
				}
				try {
					Thread.sleep((long)(Math.random() * 100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				observer_.update();
			}
			interrupt();
		}
	}
	private class Observer {
		private Object targetMutex_;
		private Observer(Object obj) {
			targetMutex_ = obj;
		}
		private void update() {
			synchronized (targetMutex_) {
				targetMutex_.notify();				
			}
		}
	}

}
