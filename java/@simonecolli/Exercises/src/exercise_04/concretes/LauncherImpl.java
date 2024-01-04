package exercise_04.concretes;

import exercise_04.abstracts.Launcher;
import exercise_04.abstracts.Monitor;

public class LauncherImpl implements Launcher{

	@Override
	public Monitor launch(Runnable r) {
		return new InnerLauncher().launch(r);
	}
	
	private class InnerLauncher implements Launcher {
		
		private boolean done_;
		private Object mutex_;
		
		private InnerLauncher() {
			done_ = false;
			mutex_ = new Object();
			
		}
		@Override
		public Monitor launch(Runnable r) {
			Monitor im = new InnerMonitor();
			new Thread(() -> {
				synchronized(mutex_) {
					r.run();
					done_ = true;
					mutex_.notifyAll();
				}
			}).start();;
			return im;
		}
		
		private class InnerMonitor implements Monitor {

			@Override
			public void await() throws InterruptedException {
				synchronized(mutex_) {
					if(!done_)
						mutex_.wait();
				}
			}
			
		}
		
	}

}
