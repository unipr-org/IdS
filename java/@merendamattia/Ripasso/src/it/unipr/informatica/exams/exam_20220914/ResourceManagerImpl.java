package it.unipr.informatica.exams.exam_20220914;

public class ResourceManagerImpl implements ResourceManager {
	private Resource[] resources;
	private Object mutex;
	
	public ResourceManagerImpl(Resource[] resources) {
		this.resources = resources;
		this.mutex = new Object();
	}
	
	@Override
	public void acquireResources(int i, int j) {
		synchronized (mutex) {
			try {
				while(!resources[i].isFree() || !resources[j].isFree()) {
					mutex.wait();
				}
			} catch (InterruptedException e) {
				System.out.println(e.getCause());
			}
			
			resources[i].acquire();
			resources[j].acquire();
			
			System.out.println("[ACQUIRE] Resources " + i + " and " + j);
		}
	}

	@Override
	public void releaseResources(int i, int j) {
		synchronized (mutex) {
			resources[i].release();
			resources[j].release();
			mutex.notifyAll();
			
			System.out.println("[RELEASE] Resources " + i + " and " + j);
		}
	}

} // ! ResourceManagerImpl
