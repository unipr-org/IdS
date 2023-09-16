package it.unipr.informatica.exercise;

public class LauncherImpl implements Launcher {
	public Monitor launch(Runnable r) {
		InnerMonitor monitor = new InnerMonitor();

		new Thread(() -> {
			try {
				r.run();

				monitor.unlock();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}).start();

		return monitor;
	}

	private class InnerMonitor implements Monitor {
		private boolean locked;
		
		private Object mutex;

		private InnerMonitor() {
			this.locked = true;
			
			this.mutex = new Object();
		}
		
		public void unlock() {
			synchronized (mutex) {
				locked = false;
				
				mutex.notifyAll();
			}
		}

		@Override
		public void await() throws InterruptedException {
			synchronized (mutex) {
				while (locked)
					mutex.wait();
			}
		}
	}
}
