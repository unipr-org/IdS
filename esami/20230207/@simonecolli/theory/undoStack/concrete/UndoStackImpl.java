package exam_2023_02_07.theory.undoStack.concrete;

import java.util.EmptyStackException;

import exam_2023_02_07.theory.undoStack.abstracts.Command;
import exam_2023_02_07.theory.undoStack.abstracts.UndoStack;

public class UndoStackImpl implements UndoStack {
	private Node<Object> top_;
	private Node<Command> undoCommands_;
	
	public UndoStackImpl() {
		top_ = null;
		undoCommands_ = null;
	}
	private void addCommand(Runnable reverseCommand) {
		Command newCommand = new UndoCommand(reverseCommand);
		Node<Command> newNode = new Node<>(newCommand);
		if(undoCommands_ == null)
			undoCommands_ = newNode;
		else {
			undoCommands_.connectNext(newNode);
			newNode.connectPrev(undoCommands_);
			undoCommands_ = undoCommands_.next_;
		}
			
	}
	
	@Override
	public Object pop() throws EmptyStackException{
		if(top_ == null)
			throw new EmptyStackException();
		Object obj = top_.val_;
		top_ = top_.prev_;
		if(top_ != null)
			top_.connectNext(null);
		
		addCommand(() -> {
			push(obj);
		});
		
		return obj;
	}
	
	@Override
	public void push(Object o) {
		Node<Object> newNode = new Node<Object>(o);
		if(top_ == null)
			top_ = newNode;
		else {
			newNode.connectPrev(top_);
			top_.connectNext(newNode);
			top_ = top_.next_;
		}
		addCommand(()->{
			pop();
		});
	}
	
	public void undo() {
		if(undoCommands_ == null)
			throw new IllegalMonitorStateException("undoCommands_ == null");
		Command command = undoCommands_.val_;
		undoCommands_= undoCommands_.prev_;
		if(undoCommands_ != null)
			undoCommands_.connectNext(null);
		
		command.execute();
	}
	
	
	private class Node<T> {
		private T val_;
		private Node<T> next_;
		private Node<T> prev_;
		
		private Node(T o) {
			val_ = o;
			next_ = null;
			prev_ = null;
		}
		
		private void connectNext(Node<T> nextNode) {
			next_ = nextNode;
		}

		private void connectPrev(Node<T> prevNode) {
			prev_ = prevNode;
		}
		
	}
}
