package it.unipr.informatica.exams.exam_20220914;

public class Worker {
	private int ID;
	private Thread exec;
	private boolean shutdown;
	private int n; // Numero di risorse
	private ResourceManager manager;
	
	public Worker(int ID) {
		if(ID < 0)
			throw new IllegalArgumentException("ID < 0");
		this.ID = ID;
		this.exec = new InnerWorker(ID);
		this.shutdown = false;
		this.manager = null;
		
		System.out.println("[Worker" + ID + "] created");
	}
	
	public void setN(int n) {
		if(n < 1)
			throw new IllegalArgumentException("n < 1");
		this.n = n;
	}
	
	public void setResourceManager(ResourceManager manager) {
		if(manager == null)
			throw new IllegalArgumentException("manager == null");
		this.manager = manager;
	}
	
	public void start() {
		exec.start();
	}
	public void stop() {
		shutdown = true;
	}
	
	private class InnerWorker extends Thread {
		private int ID_thread;
		
		public InnerWorker(int ID) {
			this.ID_thread = ID;
		}
		
		@Override
		public void run() {
			System.out.println("[Worker" + ID_thread + "t] started");
			
			while(!shutdown) {
				int i,j;
				
				do {
					i = (int) (Math.random() * n);
					j = (int) (Math.random() * n);
				} while (i == j);
				
				System.out.println("[Worker" + ID_thread + "t] try to get " + i + " and " + j);
				
				manager.acquireResources(i, j);
				
				System.out.println("[Worker" + ID_thread + "t] got " + i + " and " + j);
				
				try {
					Thread.sleep(100 + i + j);
				} catch (InterruptedException e) {
					System.out.println(e.getCause());
				}
				
				System.out.println("[Worker" + ID_thread + "t] release " + i + " and " + j);
				
				manager.releaseResources(i, j);
			}
			interrupt();
			System.out.println("[Worker" + ID_thread + "t] interrupted");
		}
	} // ! InnerWorker
	
} // ! Worker
