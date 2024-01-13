package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;
import it.unipr.informatica.exams.exam_20230126.model.AgentHandler;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AgentFactory {
	private static volatile AgentFactory instance;
	private static int nextID;
	private AgentHandler agentHandler;
	
	private AgentFactory() {
		instance = null;
		nextID = 0;
		agentHandler = new AgentHandlerImpl();
	}
	
	public static AgentFactory getInstance() {
		if (instance == null) {
			synchronized (AgentFactory.class) {
				if (instance == null) {
					instance = new AgentFactory();
				}
			}
		}
		
		return instance;
	}
	
	public Agent createAgent() {
		double state = Math.random()*2; // [0,2)
		state = state-1; // [-1,1)
		
		int id = nextID;
		++nextID;
		
		Agent res = new AgentImpl(id, state);
		
		synchronized (AgentFactory.class) {			
			agentHandler.add(res);
		}
		
		new Thread(res).start();
		
		return res;
	}
	
	public AgentHandler getAgentHandler() {
		synchronized (AgentFactory.class) {
			return this.agentHandler;
		}
	}
	
	public Agent[] getAgents() {
		return this.agentHandler.getAll();
	}
	
	public void delete() {
		synchronized (AgentFactory.class) {			
			Agent[] agents = agentHandler.getAll();
			agentHandler.delete();
			
			for (int i=0; i<agents.length; ++i) {
				agents[i].stop();
			}
		}
	}

}
