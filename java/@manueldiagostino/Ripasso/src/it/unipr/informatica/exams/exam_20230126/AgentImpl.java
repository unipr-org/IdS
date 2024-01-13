package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AgentImpl implements Agent {
	private final int ID;
	private double state;
	private AgentFactory factory;
	
	private boolean shutdown;
	
	public AgentImpl(int ID, double state) {
		if (ID < 0)
			throw new IllegalArgumentException("id < 0");
		if (state < -1 || state >= 1)
			throw new IllegalArgumentException("state < -1 || state >= 1");
		
		this.ID = ID;
		this.state = state;
		this.factory = AgentFactory.getInstance();
		this.shutdown = false;
	}
	
	private void setNewState(double w) {
		double currentState = this.state;
		this.state = currentState + (w - currentState)/2;
	}
	
	@Override
	public void run() {
		while (!shutdown) {
			Agent randomAgent = factory.getAgentHandler().getRandomAgent();
			try {
				double res = randomAgent.interact(this.state);
				setNewState(res);
				
				Thread.sleep(2);
			} catch (AgentException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public double getState() {
		return this.state;
	}

	@Override
	public double interact(double state) throws AgentException {
		double currentState = this.state;
		
		setNewState(state);
		
		return currentState;
	}

	@Override
	public void stop() {
		this.shutdown = true;
	}

}
