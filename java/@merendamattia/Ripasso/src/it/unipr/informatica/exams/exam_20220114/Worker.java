package it.unipr.informatica.exams.exam_20220114;

public class Worker {
	private int ID;
	private int R;
	private Thread exec;
	private boolean shutdown;
	private ResourceManager manager;
	private Logger logger;
	
	public Worker(int ID, int R) {
		if(ID < 0)
			throw new IllegalArgumentException("ID < 0");
		this.ID = ID;
		this.R = R;
		this.exec = new InnerWorker(ID);
		this.shutdown = false;
		this.manager = ResourceManager.getInstance();
		this.logger = Logger.getInstance();
		
		System.out.println("[Worker" + ID + "] created");
	}
	
	public void start() { exec.start(); }
	public void stop() { shutdown = true; }
	
	private class InnerWorker extends Thread {
		private int ID_t;
		public InnerWorker(int ID_t) { this.ID_t = ID_t; }
		
		@Override
		public void run() {
			System.out.println("[Worker" + ID_t + "t] started");
			
			while(!shutdown) {
				int id1 = ID;
				int id2 = (ID + 1) % R;
				int id3 = (ID + 2) % R;
				
				System.out.println("[Worker" + ID_t + "t] Try to get resources [" + id1 + ", " + id2 + ", "+ id3 + "]");
				
				Resource[] resources = manager.acquire(ID);
				
				logger.useAndPrint(resources[0], resources[1], resources[2]);
				
				manager.release(resources);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.err.println(e.getCause());
				}
			}
			
			System.out.println("[Worker" + ID_t + "t] interrupted");
			interrupt();
		}
	} // ! InnerWorker
	
} // ! Worker
