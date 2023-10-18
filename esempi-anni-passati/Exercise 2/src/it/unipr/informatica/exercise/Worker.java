package it.unipr.informatica.exercise;

public class Worker extends Thread {
	public static final int W = 9;
	
	private int id;
	
	public Worker(int id) {
		if (id < 0 || id >= W)
			throw new IllegalArgumentException("id < 0 || id >= W");
	}
	
	@Override
	public void run() {
		ResourceManager manager = ResourceManager.getInstance();

		Logger logger = Logger.getInstance();
		
		for (;;) {
			try {
				Resource[] resources = manager.acquire(id);

				logger.useAndPrint(resources[0], resources[1], resources[2]);
				
				resources[0].release();

				resources[1].release();
				
				resources[2].release();
				
				Thread.sleep(100);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}
}
