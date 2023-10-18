package it.unipr.informatica.exercise;

public class ResourceManager {
	private static final int R = 9;
	
	private static volatile ResourceManager instance;
	
	private InnerResource[] resources;
	
	private Object mutex;
	
	public static ResourceManager getInstance() {
		if (instance == null)
			synchronized (ResourceManager.class) {
				if (instance == null)
					instance = new ResourceManager();
			}
		
		return instance;
	}
	
	private ResourceManager() {
		this.mutex = new Object();
		
		this.resources = new InnerResource[R];
		
		for (int i = 0; i < R; ++i)
			resources[i] = new InnerResource(i);
	}
	
	public Resource[] acquire(int id) throws InterruptedException {
		if (id < 0 || id >= R)
			throw new IllegalArgumentException("id < 0 || id >= R");
		
		int i0 = id, i1 = (id + 1) % R, i2 = (id + 2) % R;
		
		synchronized (mutex) {
			while (!resources[i0].free || !resources[i1].free || !resources[i2].free)
				mutex.wait();
			
			resources[i0].free = resources[i1].free = resources[i2].free = false;
		}
		
		return new Resource[] { resources[i0], resources[i1], resources[i2] };
	}
	
	private class InnerResource implements Resource {
		private boolean free;
				
		private int id;
		
		private InnerResource(int id) {
			this.id = id;
			
			this.free = true;
		}
		
		@Override
		public int getID() {
			return id;
		}
		
		@Override
		public int use() {
			synchronized (mutex) {
				if (free)
					throw new IllegalStateException("free");

				return id + (int)Math.floor(100 * Math.random());
			}
		}
		
		@Override
		public void release() {
			synchronized (mutex) {
				free = true;
				
				mutex.notifyAll();
			}
		}
	}
}
