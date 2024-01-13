package it.unipr.informatica.exams.exam_20230126;
import it.unipr.informatica.exams.exam_20230126.model.*;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		int N=10;
		AgentFactory factory = AgentFactory.getInstance();
		Agent[] agents;
		
		for (int i=0; i<N; ++i)
			factory.createAgent();
		
		Thread.sleep(5000);
		
		agents = factory.getAgentHandler().getAll();
		factory.delete();
		
		for (int i=0; i<N; ++i)
			System.out.println(agents[i].getState());
		
	}
}
