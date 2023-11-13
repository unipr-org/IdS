package it.unipr.informatica.cuncurrent;

public final class LinkedBlockingQueue<T> implements BlockingQueue<T> {

	private Node<T> head_;
	private Node<T> tail_;
	private Object mutex_;
	public LinkedBlockingQueue() {
		this.head_ = null;
		this.tail_ = null;
		this.mutex_ = new Object();
	}
	
	
	@Override
	public T take() throws InterruptedException {
		synchronized (mutex_) {
			while(head_ == null)
				mutex_.wait();
			T res = head_.value_;
			
			head_ = head_.next_;
			if (head_ == null)
				tail_ = null;
			else
				mutex_.notify();
			
			return res;
		}
	}
	
	
	@Override
	public void put(T object) throws InterruptedException {
		if (object == null)
			throw new NullPointerException("Object == null");
		synchronized (mutex_) {
			Node<T> node = new Node<>();
			node.next_ = null;
			node.value_ = object;
			
			if (tail_ == null)
				head_ = tail_ = node;
			else {
				tail_.next_ = node;
				tail_ = node;
			}
			if (head_.next_ == null)
				mutex_.notify();
		}
	}
	
	@Override
	public boolean isEmpty() {
		synchronized (mutex_) {
			return head_ == null;
		}
	}
	@Override
	public void clear() {
		synchronized (mutex_) {
			head_ = tail_ = null;
		}
	}
	
	@Override
	public int remainingCapacity() {
//		ritorna il numero intero massimo possibile dato che la coda non ha un limite superiore
		return Integer.MAX_VALUE;
	}
	
	
	
	
	private static class Node<T> {
		private T value_;
		private Node<T> next_;
	}
}
