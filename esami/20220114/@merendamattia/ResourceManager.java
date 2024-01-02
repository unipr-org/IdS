package it.unipr.informatica.exams.exam_20220114;

public class ResourceManager {
	private static ResourceManager INSTANCE = null;
	private ResourceManager() { }
	public static ResourceManager getInstance() {
		if(INSTANCE == null) {
			synchronized (ResourceManager.class) {
				if(INSTANCE == null)
					INSTANCE = new ResourceManager();
			}
		}
		return INSTANCE;
	}
	// ! Singleton
	
	private int R;
	private ResourceImpl[] resources;
	
	public Resource[] createResources(int n) {
		if(n < 1)
			throw new IllegalArgumentException("n < 1");
		this.R = n;
		
		this.resources = new ResourceImpl[n];
		
		for(int i = 0; i < n; i++)
			this.resources[i] = new ResourceImpl(i);
		
		return resources;
	}
	
	public Resource[] acquire(int id) {
		int id1 = id;
		int id2 = (id + 1) % R;
		int id3 = (id + 2) % R;
		
		synchronized (INSTANCE) {
			try {
				while(!(resources[id1].isFree() && resources[id2].isFree() && resources[id3].isFree())) 
					INSTANCE.wait();
			} catch (InterruptedException e) {
				System.out.println(e.getCause());
			}
			
			System.out.println("[ACQUIRE] Resources [" + resources[id1].getID() + ", " 
													   + resources[id2].getID() + ", "
													   + resources[id3].getID() + "]");
			
			resources[id1].acquire();
			resources[id2].acquire();
			resources[id3].acquire();
			
			Resource[] result = new Resource[3];
			
			result[0] = resources[id1];
			result[1] = resources[id2];
			result[2] = resources[id3];
			
			return result;
		}
	}
	
	public void release(Resource[] resources) {
		synchronized (INSTANCE) {
			
			System.out.println("[RELEASE] Resources [" + resources[0].getID() + ", " 
													   + resources[1].getID() + ", "
													   + resources[2].getID() + "]");
			
			for(Resource res : resources) {
				res.release();
			}
			INSTANCE.notifyAll();
		}
	}
} // ! ResourceManager
