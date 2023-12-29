package exam_2022_09_14.concretes;

import exam_2022_09_14.abstracts.Worker;

public class WorkerImpl implements Worker{
	private int id_;
	private Activity activity_;
	private Object mutex_;
	public WorkerImpl(int id) {
		id_ = id;
		mutex_ = new Object();
		activity_ = null;
	}
	
	@Override
	public void startWork() {
		synchronized (mutex_) {
			activity_ = new Activity();
			activity_.start();			
		}
	}


	@Override
	public void stopWork() {
		synchronized (mutex_) {
			activity_.stopWork();
		}
	}
	
	private class Activity extends Thread {
		
		private volatile boolean shutdown_;
		private ResourceManagerSingleton rm;
		private Activity() {
			rm = ResourceManagerSingleton.getInstance();
			shutdown_ = false;			
		}
		private void stopWork() {
			shutdown_ = true;
		}
		
		@Override
		public void run() {
			while(true) {
				if(shutdown_)
					break;
				
				int i = (int) (Math.random() * ResourceManagerSingleton.RESOURCE_NUMBER);
				int j = i;
				while(j == i)
					j = (int) (Math.random() * ResourceManagerSingleton.RESOURCE_NUMBER);
				
				System.out.println("[worker " + id_ + "] acquires resources: " + i + ", " + j);
				
				rm.acquire(i, j);
//				System.out.println("[worker " + id_ + "] acquired");
				try {
					Thread.sleep(100 + i + j);
//					System.out.println("[worker " + id_ + "] sleeped");
				} catch (InterruptedException e) {
					if (!shutdown_)
	                    throw new RuntimeException(e);
				}				

				System.out.println("[worker " + id_ + "] free resources: " + i + ", " + j);
				rm.free(i, j);
//				System.out.println("[worker " + id_ + "] freed");
			}
			interrupt();
		}
	}
}
