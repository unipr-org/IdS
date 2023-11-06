package it.unipr.informatica.concurrent;

public class LinkedBlockingQueue<T> implements BlockingQueue<T>{
	private Node<T> head;
	private Node<T> tail;
	private Object mutex;
	
	public LinkedBlockingQueue() { 
		this.head = null;
		this.tail = null;
		mutex = new Object();
	}
	
	@Override
	public void put(T object) { 
		if(object == null)
			throw new NullPointerException("object == null");
		
		synchronized (mutex) {
			Node<T> node = new Node<>();
			
			node.next = null;
			node.value = object;
			
			if(tail == null)
				tail = node;
			else {
				tail.next = node;
				tail = node;
			}
			
			mutex.notifyAll();
			
			// Per essere piu ottimizzato possiamo fare cosi:
//			if(head.next == null)
//				mutex.notify();
			// In questo modo vado a svegliare solo un thread e non tutti
		}
	}
	
	@Override
	public T take() throws InterruptedException { 
		synchronized (mutex) {
			while (head == null)
				mutex.wait();
			
			T result = head.value;
			
			head = head.next;
			
			if(head == null)
				tail = null;
			else
				mutex.notifyAll();
			
			return result;
		}
	}
	
	@Override
	public void clear() { 
		synchronized (mutex) {
			tail = null;
			head = null;
		}
	}
	
	@Override
	public int remainingCapacity() { 
		// Ritorniamo il numero massimo intero positivo, dato che non abbiamo un limite superiore
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
	};
	
}
