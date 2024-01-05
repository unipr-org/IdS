package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ResourceManagerImpl implements ResourceManager {
	private Resource[] systemResources;
	private static volatile ResourceManagerImpl instance;

	private ResourceManagerImpl(int n) {
		if (n < 1)
			throw new IllegalArgumentException("n<1");

		systemResources = new Resource[n];
		for (int i = 0; i < n; ++i)
			systemResources[i] = new ResourceImpl();
	}

	public static ResourceManagerImpl getInstance(int n) {
		if (n < 1)
			throw new IllegalArgumentException("n<1");

		if (instance == null) {
			synchronized (ResourceManagerImpl.class) {
				if (instance == null)
					instance = new ResourceManagerImpl(n);
			}
		}

		return instance;
	}

	@Override
	public void acquireResource(int i, int j) throws InterruptedException {
		if (i == j)
			throw new IllegalArgumentException("i==j");
		if (i < 0 || j < 0)
			throw new IllegalArgumentException("i<0 || j<0");
		if (i >= systemResources.length || j >= systemResources.length)
			throw new IllegalArgumentException("i>=N || j>=N");

		synchronized (instance) { // sicuramente instance != null
			try {
				while (systemResources[i].isAcquired() || systemResources[j].isAcquired())
					instance.wait();
			} catch (InterruptedException e) {
				throw e;
			}

			try {
				systemResources[i].acquire();
				try {
					systemResources[j].acquire();
				} catch (InterruptedException e) {
					systemResources[i].release();
					System.out.println("systemResources[j].acquire() failed");
					throw e;
				}
			} catch (InterruptedException e) {
				System.out.println("systemResources[i].acquire() failed");
				throw e;
			}
		}
	}

	@Override
	public void freeResource(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("i==j");
		if (i < 0 || j < 0)
			throw new IllegalArgumentException("i<0 || j<0");
		if (i >= systemResources.length || j >= systemResources.length)
			throw new IllegalArgumentException("i>=N || j>=N");

		synchronized (instance) {
			systemResources[i].release();
			systemResources[j].release();

			instance.notifyAll();
		}
	}

	public static void main(String[] args) {
		ResourceManagerImpl manager = ResourceManagerImpl.getInstance(10);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("Thread acquiring resources (3,1)");
					manager.acquireResource(3, 1);
					System.out.println("Thread acquired resources (3,1)");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				manager.freeResource(3, 1);
				System.out.println("Thread exiting");
			}
		};

		new Thread(runnable).start();

		try {
			System.out.println("Main acquiring resources (0,1)");
			manager.acquireResource(0, 1);
			System.out.println("Main acquired resources (0,1)");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.freeResource(0, 1);
		System.out.println("Main exiting");
	}
}
