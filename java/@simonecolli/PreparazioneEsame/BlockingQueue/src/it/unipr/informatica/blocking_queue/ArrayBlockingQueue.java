package it.unipr.informatica.blocking_queue;

import it.unipr.informatica.locks.Condition;
import it.unipr.informatica.locks.Lock;
import it.unipr.informatica.locks.ReentrantLock;

public class ArrayBlockingQueue<T> implements BlockingQueue<T>{

	private int capacity_;
	private int size_;
	private Node<T> head_;
	private Node<T> tail_;
	
	public Lock lock_;
	
	private Condition isNotFull_;
	private Condition isNotEmpty_;
	
	public ArrayBlockingQueue(int maxCapacity) {
		if (maxCapacity > Integer.MAX_VALUE || maxCapacity < 0)
			throw new IllegalArgumentException("maxSize > Integer.MAX_VALUE || maxSize < 0");
		
		this.capacity_ = maxCapacity;
		this.head_ = this.tail_ = null;
		this.lock_ = new ReentrantLock();
		this.size_ = 0;
		this.isNotFull_ = lock_.newCondition();
		this.isNotEmpty_ = lock_.newCondition();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T take() throws InterruptedException {
		T res;
		lock_.lock();

		while(this.isEmpty())
			isNotEmpty_.await();
		
		res = (T) head_.value_;
		
		head_ = head_.next_;
		--size_;
		
		
		isNotFull_.signal();
		lock_.unlock();
		
		return res;
	}

	@Override
	public void put(T elem) throws InterruptedException {
		
		lock_.lock();
		while(this.isFull())
			isNotFull_.await();
		
		Node<T> node = new Node<>();
		node.next_ = null;
		node.value_ = elem;
		
		if(head_ == null)
			head_ = tail_ = node;
		else {
			tail_.next_ = node;
			tail_ = tail_.next_;
		}
			
		++size_;
		
		isNotEmpty_.signal();
		lock_.unlock();
	}

	@Override
	public void clear() {
		lock_.lock();
		head_ = tail_ = null;
		size_ = 0;
		isNotFull_.signalAll();
		lock_.unlock();
	}

	@Override
	public int remainingCapacity() {
		lock_.lock();
		int cap = capacity_ - size_;
		lock_.unlock();
		return cap;
	}

	@Override
	public boolean isEmpty() {
		
		lock_.lock();
		boolean res = (size_ == 0);
		lock_.unlock();
		return res;
	}

	@Override
	public boolean isFull() {
		lock_.lock();
		boolean res = (size_ == capacity_);
		lock_.unlock();
		return res;
	}
	@Override
	public String toString() {
		lock_.lock();
		String s = "size: " + size_ + "\ncapacity: " + capacity_ + "\n";
		Node<T> tmp = head_;
		while (tmp != null) {
			s = s + tmp.value_.toString()+ ", ";
			tmp = tmp.next_;
		}
		lock_.unlock();
		return s;
	}
	
	private class Node<T> {
		private Node<T> next_;
		private T value_;
		
	}
	
}
