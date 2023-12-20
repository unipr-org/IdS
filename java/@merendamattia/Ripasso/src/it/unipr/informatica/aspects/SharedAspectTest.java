package it.unipr.informatica.aspects;

import java.util.LinkedList;
import java.util.Queue;

public class SharedAspectTest {

	private void go() {
		
		Queue<String> queueShared = SharedAspect.attach(new LinkedList<>());
		Queue<String> queue = LoggingAspect.attach(queueShared);
		
		for(int i = 0; i < 10; ++i) {
			int id = i;
			
			Runnable r = () -> {
				try {
					
					int time = (int) (Math.random() * 3000);
					Thread.sleep(time);
					queue.add("Thread" + id + " - " + time);
					
				} catch (InterruptedException e) {
					e.getCause();
				}
			};
			
			new Thread(r).start();
		}
		
		// Aspetto 3 secondi prima di fare il polling
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.getCause();
		}
		
		for(int i = 0; i < 10; ++i) {
			queue.toString();
			queue.poll();
		}
		
	} // ! go()
	
	
	public static void main(String[] args) {
		new SharedAspectTest().go();
	} // ! main()

} // ! SharedAspectTest
