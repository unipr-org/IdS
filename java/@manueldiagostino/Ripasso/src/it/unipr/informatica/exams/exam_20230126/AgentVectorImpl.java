package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;
import it.unipr.informatica.exams.exam_20230126.model.AgentVector;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AgentVectorImpl implements AgentVector {
	private Agent[] elements;
	private int count;
	
	private final int MIN_SIZE = 10;
	
	public AgentVectorImpl() {
		this.elements = new Agent[MIN_SIZE];
		this.count = 0;
	}
	
	@Override
	public void add(Agent agent) {
		synchronized (elements) {
			if (this.count == elements.length)
				doubleSize();
			
			elements[count++] = agent;
		}
	}

	@Override
	public int getSize() {
		synchronized (elements) {			
			return count;
		}
	}
	
	private void doubleSize() {
		synchronized (elements) {
			Agent[] newArray = new Agent[(elements.length*2) +1];
			
			for (int i=0; i<elements.length; ++i)
				newArray[i] = elements[i];
			
			elements = newArray;
		}
	}

	@Override
	public Agent get(int i) {
		if (i<0 || i>count)
			throw new IllegalArgumentException("i<0 || i>count");
		
		synchronized (elements) {			
			return elements[i];
		}
	}

	@Override
	public Agent[] getArray() {
		synchronized (elements) {			
			return this.elements;
		}
	}
	
	public static void main(String[] args) {
		AgentVector agents = new AgentVectorImpl();
		
		for (int i=0; i<7; ++i)
			agents.add(AgentFactory.getInstance().createAgent());
				
		for (int i=0; i<agents.getSize(); ++i)
			System.out.println(agents.get(i).getState());
	}
}
