package it.unipr.informatica.exams.exam_20220914;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		int workerNum = 20;
		int n = 50;
		
		ResourceManagerImpl manager = ResourceManagerImpl.getInstance(n);
		System.out.println("Created manager for " + n + " resources");
		
		Worker[] workers = new Worker[workerNum];
		System.out.println("Created "+ workerNum + " workers");
		
		for (int i=0; i<workerNum; ++i) {
			workers[i] = new WorkerImpl(manager, i, n);
			workers[i].run();
		}
		
		Thread.sleep(4000);
		for (int i=0; i<workerNum; ++i)
			workers[i].stop();
		
		System.out.println("EXITING ...");
	}
}
