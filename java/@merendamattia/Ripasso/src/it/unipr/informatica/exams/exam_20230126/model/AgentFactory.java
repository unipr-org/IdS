package it.unipr.informatica.exams.exam_20230126.model;

import it.unipr.informatica.exams.exam_20230126.SimpleAgent;
import it.unipr.informatica.exams.exam_20230126.SimpleAgentVector;

public class AgentFactory {
	private static volatile AgentFactory INSTANCE;
	private static int agent_counter;
	private AgentHandler agent_handler;
	
	private AgentFactory() { }
	public static AgentFactory getInstance() {
		if(INSTANCE == null) {
			synchronized (AgentFactory.class) {
				if(INSTANCE == null) {
					INSTANCE = new AgentFactory();
					agent_counter = 0;
				}
			}
		}
		return INSTANCE;
	}
	
	public Agent createAgent() {
		if(agent_counter == 0)
			agent_handler = new AgentHandler();
		
		Agent result = new SimpleAgent(agent_counter++);
		agent_handler.add(result);
		
		return result;
	}
	
	public Agent getRandomAgent() {
		int random = (int) (Math.random() * agent_counter);
		
		return agent_handler.get(random);
	}
	
	public void printAgents() {
		agent_handler.print();
	}
	
	public void start() {
		agent_handler.start();
	}
	
	public void shutdown() {
		agent_handler.stop();
	}
	
	private class AgentHandler {
		private AgentVector list;
		
		public AgentHandler() {
			this.list = new SimpleAgentVector();
		}
		
		private void add(Agent item) {
			list.add(item);
		}
		
		private Agent get(int index) {
			return list.get(index);
		}
		
		private void print() {
			for(int i = 0; i < list.size(); i++)
				System.out.printf("[AGENT %s] State = %s \n", list.get(i).getID(), list.get(i).getState());
		}
		
		private void start() {
			for(int i = 0; i < list.size(); i++)
				new Thread(list.get(i)).start();
		}
		
		private void stop() {
			for(int i = 0; i < list.size(); i++)
				list.get(i).stop();
		}
	} // ! AgentHandler
	
} // ! AgentFactory
