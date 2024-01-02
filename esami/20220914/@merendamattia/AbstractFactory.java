package it.unipr.informatica.exams.exam_20220914;

public class AbstractFactory {
	public static Resource[] createResources(int num_resources) {
		if(num_resources < 1)
			throw new IllegalArgumentException("num_resources < 1");
		
		Resource[] resources = new Resource[num_resources];
		
		for(int i = 0; i < num_resources; i++)
			resources[i] = new Resource(i);
		
		return resources;
	}
	
	public static Worker[] createWorkers(int num_workers) {
		if(num_workers < 1)
			throw new IllegalArgumentException("num_workers < 1");
		
		Worker[] workers = new Worker[num_workers];
		
		for(int i = 0; i < num_workers; i++)
			workers[i] = new Worker(i);
		
		return workers;
	}
} // ! AbstractFactory
