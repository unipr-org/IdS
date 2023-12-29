package exam_2022_09_14.concretes;


import exam_2022_09_14.abstracts.Resource;
import exam_2022_09_14.abstracts.ResourceManager;
import exam_2022_09_14.concurrency.Condition;
import exam_2022_09_14.concurrency.Lock;
import exam_2022_09_14.concurrency.ReentrantLock;

public class ResourceManagerSingleton {
	
	private static volatile ResourceManagerSingleton INSTANCE = null;
	
	public static ResourceManagerSingleton getInstance() {
		
		if(INSTANCE == null)
			synchronized (ResourceManagerSingleton.class) {
				if(INSTANCE == null)
					INSTANCE = new ResourceManagerSingleton();
			}
		
		return INSTANCE;
	}
	public final static int RESOURCE_NUMBER = 50;
	
	private InnerResourceManager manager_;
	
	private ResourceManagerSingleton() {
		manager_ = new InnerResourceManager();
	}
	public void acquire(int i, int j) {
		manager_.acquireResource(i, j);
	}
	
	public void free(int i, int j) {
		manager_.freeResources(i, j);
	}

	private class InnerResourceManager implements ResourceManager {
		
		private Resource[] resources_;
		private boolean[] acquired_;
		
		private Lock lock_;
		private Condition condition_;
		
		private InnerResourceManager() {
			lock_ = new ReentrantLock();
			condition_ = lock_.newCondition();
			
			resources_ = new Resource[RESOURCE_NUMBER];
			acquired_ = new boolean[RESOURCE_NUMBER];
				
			for(int i = 0; i < RESOURCE_NUMBER; ++i) {
				resources_[i] = new ResourceImpl(i);
				acquired_[i] = false;
			}
				
		}
		
		@Override
		public void acquireResource(int i, int j) {
			try {
				lock_.lock();
				try {
					while(acquired_[i] || acquired_[j])
						condition_.await();

					
					acquired_[i] = true;
					acquired_[j] = true;

					resources_[i].acquire();
					resources_[j].acquire();					
					
				} finally {					
					lock_.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
							
		}

		@Override
		public void freeResources(int i, int j) {

			try {
				lock_.lock();
				if(!acquired_[i] || !acquired_[j])
					throw new IllegalMonitorStateException("!acquired_[i] || !acquired[j]");
				
				acquired_[i] = false;
				acquired_[j] = false;
				resources_[i].release();
				resources_[j].release();
				
				condition_.signalAll();
				
				lock_.unlock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		}
	}

}
