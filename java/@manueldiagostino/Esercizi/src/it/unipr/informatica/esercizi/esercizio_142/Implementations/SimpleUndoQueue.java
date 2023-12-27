package it.unipr.informatica.esercizi.esercizio_142.Implementations;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import it.unipr.informatica.esercizi.esercizio_142.Abstractions.UndoQueue;
import it.unipr.informatica.esercizi.esercizio_142.Command.Command;
import it.unipr.informatica.esercizi.esercizio_142.Command.DequeueCommand;
import it.unipr.informatica.esercizi.esercizio_142.Command.EnqueueCommand;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class SimpleUndoQueue<T> implements UndoQueue<T> {

	private CommandHistory commandHistory;
	private List<T> queue;
	
	public SimpleUndoQueue() {
		commandHistory = new CommandHistory();
		queue = new LinkedList<T>();
	}
	
	@Override
	public void enqueue(T elem) {
		EnqueueCommand enqueueComm = new EnqueueCommand<T>(queue, elem);
		enqueueComm.execute();
		commandHistory.push(enqueueComm);
	}

	@Override
	public T dequeue() {
		DequeueCommand<T> dequeueComm = new DequeueCommand<T>(queue);
		dequeueComm.execute();
		commandHistory.push(dequeueComm);
		
		return dequeueComm.getElem();
	}

	@Override
	public void clear() {
		queue.clear();
		commandHistory.clear();
	}

	@Override
	public boolean isEmpty() {
		return commandHistory.isEmpty();
	}

	@Override
	public void undo() {
		Command command = commandHistory.pop();
		command.undo();
	}
	
	@Override
	public String toString() {
		return this.queue.toString();
	}
}
