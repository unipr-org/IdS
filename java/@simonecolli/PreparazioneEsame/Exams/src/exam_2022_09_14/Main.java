package exam_2022_09_14;


import exam_2022_09_14.abstracts.Worker;
import exam_2022_09_14.abstracts.WorkerAbstractFactory;
import exam_2022_09_14.concretes.ResourceManagerSingleton;
import exam_2022_09_14.concretes.WorkerFactory;

public class Main {
	public static void main(String[] args) {
		WorkerAbstractFactory wf = new WorkerFactory();
		Worker[] workers = new Worker[10];
		
		for(int i = 0; i < workers.length; ++i)
			workers[i] = wf.makeWorker();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("start work");
		
		for(int i = 0; i < workers.length; ++i)
			workers[i].startWork();
		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("stop work");
		
//		ResourceManagerSingleton.getInstance().stop();
		for(int i = 0; i < workers.length; ++i)
			workers[i].stopWork();
	}
}
