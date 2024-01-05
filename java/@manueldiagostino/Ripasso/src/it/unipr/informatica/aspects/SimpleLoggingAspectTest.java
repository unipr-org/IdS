package it.unipr.informatica.aspects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.unipr.informatica.blockingQueue.ArrayBlockingQueue;
import it.unipr.informatica.blockingQueue.BlockingQueue;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class SimpleLoggingAspectTest {
	public void go() {
		BlockingQueue<Integer> queue = SimpleLoggingAspect.attach(new ArrayBlockingQueue<>(20));

		try {
			queue.isEmpty();
			queue.put(0);
			queue.put(1);
			queue.put(2);

			queue.isEmpty();

			queue.take();
			queue.take();
			queue.take();
			queue.isEmpty();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void go2() {
		List<Integer> list = SimpleLoggingAspect.attach(new LinkedList<Integer>());
		
		list.add(1);
		list.add(1);
		
	}

	public static void main(String[] args) {
		new SimpleLoggingAspectTest().go2();
	}
}
