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
		
		// System.out.println("Producer prova ad acquisire mutex");
		synchronized (mutex) {
			// System.out.println("Producer acquisisce mutex");
			Node<T> node = new Node<>();
			
			node.next = null;
			node.value = object;
			
			if(tail == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			
			mutex.notifyAll();
			
			// Per essere piu' ottimizzato possiamo fare cosi:
//			if(head.next == null)
//				mutex.notify();
			// In questo modo vado a svegliare solo un thread e non tutti
		}
	}
	
	@Override
	public T take() throws InterruptedException { 
		// System.out.println("Consumer prova ad acquisire mutex");
		synchronized (mutex) {
			// System.out.println("Consumer acquisisce mutex");
			while (head == null) {
				// System.out.println("Consumer trova coda vuota -> si addormenta");
				mutex.wait();
			}
			
			// System.out.println("Consumer viene risvegliato");
			
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
	
	@Override
	public void printQueue() {
		synchronized (mutex) {
			Node<T> tmp = new Node<>();
			tmp = head;
				
			System.out.print("[");
			while(tmp != null) {
				System.out.print(tmp.value);
					
				if(tmp.next != null)
					System.out.print(", ");
				
				tmp = tmp.next;
			}
			System.out.println("]");
		}
		
	}
		
	private static class Node<T> { 
		private T value;
		private Node<T> next;
	};
	
}
