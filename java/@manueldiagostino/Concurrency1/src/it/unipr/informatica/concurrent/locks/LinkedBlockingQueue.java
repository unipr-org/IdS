package it.unipr.informatica.concurrent.locks;

public class LinkedBlockingQueue<T> implements BlockingQueue<T> {
	
	Node<T> head;
	Node<T> tail;
	
	final Object mutex = new Object();
	
	@Override
	public void put(T elem) {
		if (elem == null)
			throw new NullPointerException("elem == null");
		
		Node<T> node = new Node<>();
		node.value = elem;
		node.next = null;
		
		synchronized (mutex) {
			if (tail == null) {
				head = tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			
//			System.err.println(elem.toString() + " added to the queue");
			
			if (head.next == null)
				mutex.notify();				
		}
	}

	@Override
	public T take() throws InterruptedException {
		T result;
		
		synchronized (mutex) {
			while (isEmpty())
				mutex.wait();
			
			result = head.value;
			head = head.next;
			
			if (head == null)
				tail = null;
			else
				mutex.notify();
		}
		
//		System.err.println(result.toString() + " taken from the queue");
		
		return result;
	}

	@Override
	public void clear() {
		synchronized (mutex) {
			head = tail = null;		
		}
		System.err.println("Queue cleared");
	}

	@Override
	public int remainingCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isEmpty() {
		synchronized (mutex) {			
			return head == null;
		}
	}
	
	private static class Node<T> {
		private T value;
		private Node<T> next;
	}
	
}
