package it.unipr.informatica.exams.exam_20220127;

import it.unipr.informatica.exams.exam_20220127.model.Slave;

public class SlaveManager {
	public static SlaveManager INSTANCE = null;
	private SlaveManager() { };
	public static SlaveManager getInstance() {
		if(INSTANCE == null) {
			synchronized (SlaveManager.class) {
				if(INSTANCE == null)
					INSTANCE = new SlaveManager();
			}
		}
		return INSTANCE;
	}

	public Slave newSlave() {
		return new Slave() {
			
			@Override
			public int finish() {
				return (int) (Math.random() * 1000);
			}
			
			@Override
			public void executePartC() {
				System.out.println("Part C executed");
			}
			
			@Override
			public void executePartB() {
				System.out.println("Part B executed");
			}
			
			@Override
			public void executePartA() {
				System.out.println("Part A executed");
			}
		}; // ! Slave
	}
	
} // ! SlaveManager

