package it.unipr.informatica.exams.exam_20220114;

public class Main {
	private void go() {
		int W, R;
		W = R = 9;
		
		Worker[] workers = new Worker[W];
		ResourceManager manager = ResourceManager.getInstance();
		manager.createResources(R);
		
		for(int i = 0; i < W; i++) 
			workers[i] = new Worker(i, R);
		
		for(int i = 0; i < W; i++) 
			workers[i].start();
		
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			System.out.println(e.getCause());
		}
		
		for(int i = 0; i < W; i++) 
			workers[i].stop();
		
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
