package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.AgentFactory;

public class Main {
	private void go() {
		int N = 50;
		AgentFactory factory = AgentFactory.getInstance();
		
		for(int i = 0; i < N; i++)
			factory.createAgent();
		
		System.out.println("Initial states");
		factory.printAgents();
		
		factory.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		factory.shutdown();
		
		System.out.println("Final states");
		factory.printAgents();
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
