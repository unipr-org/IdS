package it.unipr.informatica.exams.exam_20220914;

public class Main {
	private void go() {
		int m = 3;
		int n = 10;
		
		Worker[] workers = AbstractFactory.createWorkers(m);
		Resource[] resources = AbstractFactory.createResources(n);
		ResourceManager manager = new ResourceManagerImpl(resources);
		
		for(int i = 0; i < m; i++) {
			workers[i].setN(n);
			workers[i].setResourceManager(manager);
		}
		
		for(int i = 0; i < m; i++) {
			workers[i].start();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println(e.getCause());
		}
		
		for(int i = 0; i < m; i++) {
			workers[i].stop();
		}
		
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
