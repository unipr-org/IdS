package it.unipr.informatica.esercizi.esercizio_142.Command;

import java.awt.IllegalComponentStateException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class DequeueCommand<T> implements Command{
	private T dequeuedElement;
	private List<T> target;

	public DequeueCommand(List<T> targetQueue) {
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
