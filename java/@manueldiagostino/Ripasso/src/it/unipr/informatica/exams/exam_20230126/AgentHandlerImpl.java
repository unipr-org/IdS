package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;
import it.unipr.informatica.exams.exam_20230126.model.AgentHandler;
import it.unipr.informatica.exams.exam_20230126.model.AgentVector;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AgentHandlerImpl implements AgentHandler {
	private AgentVector elements;
	
	public AgentHandlerImpl() {
		this.elements = new AgentVectorImpl();
	}
	
	@Override
	public Agent getRandomAgent() {
		int random = (int) (Math.random()*elements.getSize());
		return elements.get(random);
	}

	@Override
	public void add(Agent agent) {
		elements.add(agent);
	}

	@Override
	public Agent[] getAll() {
		return this.elements.getArray();
	}

	@Override
	public Agent get(int i) {
		return elements.get(i);
	}

	@Override
	public void delete() {
		elements = new AgentVectorImpl();
	}

}
