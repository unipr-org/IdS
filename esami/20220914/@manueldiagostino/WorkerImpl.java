package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class WorkerImpl implements Worker {
	private Thread worker;
	private boolean stop;
	private int id;
	
	public WorkerImpl(ResourceManager manager, int id, int n) {
		this.stop = false;
		this.id = id;
		
		worker = new Thread() {
			@Override
			public void run() {
				while (true) {
					synchronized (WorkerImpl.class) {
						if (stop)
							return;						
					}
					
					int i = (int)(Math.random()*n);
					int j;
					do {
						j = (int)(Math.random()*n);
					} while (i==j);
					
					
					System.out.println("["+id+"] acquiring resources (" +i+", "+j+")");
					try {
						manager.acquireResource(i, j);
						try {
							
							System.out.println("["+id+"] acquired resources (" +i+", "+j+")");
							Thread.sleep(100+i+j);
							
						} catch (InterruptedException e) {
							String msg = "["+id+"] interrupted while sleeping";
							System.out.println(msg);
							throw new Exception(msg);
						}
				
						manager.freeResource(i, j);
						System.out.println("["+id+"] exiting after freed (" +i+", "+j+")");
					} catch (InterruptedException e) {
						System.out.println("["+id+"] interrupted without acquiring resources");
						return;
					} catch (Exception e) {
						return;
					}
				}
			}
		};
	}
	
	@Override
	public void run() {
		worker.start();
	}

	@Override
	public void stop() {
		synchronized (WorkerImpl.class) {
			stop = false;			
		}
		
		worker.interrupt();
	}
}
