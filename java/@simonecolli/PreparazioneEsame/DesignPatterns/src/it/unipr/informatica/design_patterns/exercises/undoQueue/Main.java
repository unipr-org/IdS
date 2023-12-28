package it.unipr.informatica.design_patterns.exercises.undoQueue;

import it.unipr.informatica.design_patterns.exercises.undoQueue.abstracts.UndoQueue;
import it.unipr.informatica.design_patterns.exercises.undoQueue.concrete.SimpleQueue;

public class Main {
	public static void main(String[] args) {
		UndoQueue<String> queue = new SimpleQueue<>();
		queue.enqueue("ciao");
		System.out.println(queue.isEmpty()); // false
		queue.undo();
		System.out.println(queue.isEmpty()); // true
		queue.enqueue("ciao");
		queue.enqueue("ciao");
		System.out.println(queue.isEmpty()); // false
		queue.clear();
		System.out.println(queue.isEmpty()); // true
		queue.undo();
		System.out.println(queue.isEmpty()); // false
		
	}
}
