package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;
import it.unipr.informatica.exams.exam_20230126.model.AgentException;
import it.unipr.informatica.exams.exam_20230126.model.AgentFactory;

public class SimpleAgent implements Agent{
	private int ID;
	private double state;
	private Object mutex;
	private boolean shutdown;
	private AgentFactory factory;
	
	public SimpleAgent(int ID) {
		this.ID = ID;
		this.state = (Math.random() * 2) - 1;
		this.mutex = new Object();
		this.shutdown = false;
		this.factory = AgentFactory.getInstance();
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized (mutex) {
				if(shutdown)
					return;
			}
			Agent w = factory.getRandomAgent();
			try {
				state = state + 0.5 * (w.interact(state) - state);
			} catch (AgentException e) {
				System.err.println(e.getCause());
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				System.err.println(e.getCause());
			}
		}
	}

	@Override
	public int getID() {
		synchronized (mutex) {
			return ID;
		}
	}

	@Override
	public double getState() {
		synchronized (mutex) {
			return state;
		}
	}

	@Override
	public double interact(double v) throws AgentException {
		synchronized (mutex) {
			double current_state = state;
			state = state + 0.5 * (v - state);
			return current_state;
		}
	}

	@Override
	public void stop() {
		synchronized (mutex) {
			if(shutdown)
				throw new IllegalStateException("shutdown == true");
			shutdown = true;
		}
	}
	
} // ! SimpleAgent
