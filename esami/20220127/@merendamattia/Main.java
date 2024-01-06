package it.unipr.informatica.exams.exam_20220127;

import it.unipr.informatica.exams.exam_20220127.model.Commander;
import it.unipr.informatica.exams.exam_20220127.model.Slave;

public class Main {
	private void go() {
		int S = 10;
		
		Commander god = SimpleCommander.getInstance();
		SlaveManager manager = SlaveManager.getInstance();
		
		Slave[] slaves = new Slave[S];
		
		for(int i = 0; i < S; i++)
			slaves[i] = manager.newSlave();
		
		for(int i = 0; i < S; i++)
			System.out.println(god.command(slaves[i]));
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
