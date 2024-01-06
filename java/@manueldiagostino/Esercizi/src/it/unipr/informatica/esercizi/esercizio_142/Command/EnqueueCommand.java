package it.unipr.informatica.esercizi.esercizio_142.Command;

import java.awt.IllegalComponentStateException;
import java.util.Deque;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class EnqueueCommand<T> implements Command {
	private T enqueuedElement;
	private Deque<T> target;
	
	public EnqueueCommand(Deque<T> targetQueue, T elem) {
		enqueuedElement = elem;
		target = targetQueue;
	}
	
	@Override
	public void execute() {
		target.addLast(enqueuedElement);
	}

	@Override
	public void undo() {
		if (enqueuedElement == null)
			throw new IllegalComponentStateException("enqueuedElement == null");
		
		target.removeLast();
	}

}
