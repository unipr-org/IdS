package exam_2022_06_22.concrete;

import exam_2022_06_22.abstracts.Waiter;

public class WaiterImpl implements Waiter {
	private Thread cycle_;
	private Object mutex_;
	private boolean shutdown_;

	public WaiterImpl() {
		shutdown_ = false;
		cycle_ = null;
		mutex_ = new Object();
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
	private class Cycle extends Thread {
		
		private Cycle() {
		}
		
		@Override
		public void run() {
			int counter = 1;
			while(true) {
				synchronized (mutex_) {
					if(shutdown_)
						break;
				}
				try {
					BarrierImpl.getInstance().await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("counter :" + counter++);
			}
			interrupt();
		}

	}


}
