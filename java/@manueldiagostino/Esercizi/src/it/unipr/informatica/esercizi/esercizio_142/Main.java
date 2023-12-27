package it.unipr.informatica.esercizi.esercizio_142;

import it.unipr.informatica.esercizi.esercizio_142.Abstractions.UndoQueue;
import it.unipr.informatica.esercizi.esercizio_142.Implementations.SimpleUndoQueue;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	
	public void testEnqueue() {
		SimpleUndoQueue<Integer> queue = new SimpleUndoQueue<Integer>();
		
		queue.enqueue(0);
		queue.enqueue(0);
		queue.enqueue(1);
		System.out.println(queue);
		
		queue.undo();
		queue.undo();
		System.out.println(queue);
		
		queue.enqueue(7);
		System.out.println(queue);
	}
	
	public void testDequeue() {
		SimpleUndoQueue<Integer> queue = new SimpleUndoQueue<Integer>();
		
		queue.enqueue(1);
		queue.enqueue(0);
		queue.enqueue(1);
		System.out.println(queue);
		
		queue.dequeue();
		System.out.println(queue);
		
		queue.undo();
		System.out.println(queue);
	}
	
	public static void main(String[] args) {
		new Main().testDequeue();
	}
}
