package it.unipr.informatica.exercises.esercizio_142.Command;

import java.awt.IllegalComponentStateException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class DequeueCommand<T> implements Command {
	private T dequeuedElement;
	private Deque<T> target;

	public DequeueCommand(Deque<T> targetQueue) {
		this.dequeuedElement = null;
		this.target = targetQueue;
	}

	@Override
	public void execute() {
		dequeuedElement = target.removeFirst();
	}

	@Override
	public void undo() {
		if (dequeuedElement == null)
			throw new IllegalComponentStateException("dequeuedElement == null");

		target.addFirst(dequeuedElement);
	}

	public T getElem() {
		if (dequeuedElement == null)
			throw new IllegalComponentStateException("dequeuedElement == null");

		return this.dequeuedElement;
	}
}
