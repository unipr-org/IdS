package it.unipr.informatica.design_patterns.exercises.undoQueue.concrete;

import it.unipr.informatica.design_patterns.exercises.undoQueue.abstracts.UndoCommand;
import it.unipr.informatica.design_patterns.exercises.undoQueue.abstracts.UndoQueue;

public class SimpleQueue<T> implements UndoQueue<T> {
	private UndoCommandImpl undoHandler_;
	private Node<T> head_;
	private Node<T> tail_;
	
	public SimpleQueue() {
		this.undoHandler_ = new UndoCommandImpl();
		this.head_ = this.tail_ = null;
	}

	@Override
	public void undo() {
		undoHandler_.undo();
	}

	@Override
	public void enqueue(T elem) {
		
		Node<T> newNode = new Node<>();
		newNode.elem_ = elem;
		newNode.next_ = null;
		
		undoHandler_.setState(head_, tail_);
		if(head_ == null)
			head_ = tail_ = newNode;
		else {
			
		}
	}

	@Override
	public T dequeue() {
		T elem = head_.elem_;
		undoHandler_.setState(head_, tail_);
		head_ = head_.next_;
		if(head_ == null)
			tail_ = null;
		return elem;
	}

	@Override
	public void clear() {
		undoHandler_.setState(head_, tail_);
		head_ = tail_ = null;
	}

	@Override
	public boolean isEmpty() {
		return head_ == null;
	}
	
	private class Node<T> {
		private T elem_;
		private Node<T> next_;
	}
	
	private class UndoCommandImpl implements UndoCommand {
		
		private Node<T> prevHead_;
		private Node<T> prevTail_;
		
		private UndoCommandImpl() {
			this.prevHead_ = this.prevTail_ = null;
			
		}
		@Override
		public void undo() {
			head_ = prevHead_;
			tail_ = prevTail_;
		}
		private void setState(Node<T> head, Node<T> tail) {
			if(head == null && tail == null) {
				prevHead_= head;
				prevTail_ = tail;
			} else {
				prevHead_ = null;
				prevTail_ = null;
				Node<T> tmp = head;
				while (tmp != null) {
					
					Node<T> node = new Node<>();
					node.elem_ = tmp.elem_;
					node.next_ = null;
					
					if(prevHead_ == null) {
						this.prevHead_ = this.prevTail_ = node;
					} else {
						prevTail_.next_ = node;
						prevTail_ = prevTail_.next_;
					}
					
					tmp = tmp.next_;
				}
			}
				
		}
		
	}
}
