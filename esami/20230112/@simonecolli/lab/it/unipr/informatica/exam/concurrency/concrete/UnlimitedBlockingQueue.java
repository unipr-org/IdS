package exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.concrete;

import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.BlockingQueue;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Condition;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Lock;

public class UnlimitedBlockingQueue<T> implements BlockingQueue<T>{
	
	private Node<T> head_;
	private Node<T> tail_;
	private Lock lock_;
	private Condition isNotEmpty_;
	private int size_;
	public UnlimitedBlockingQueue() {
		head_ = tail_ = null;
		lock_ = new ReentrantLock();
		isNotEmpty_ = lock_.newCondition();
		size_ = 0;
	}
	
	@Override
	public T take() throws InterruptedException {
		T elem = null;
		lock_.lock();
		
		while(isEmpty())
			isNotEmpty_.await();
		
		elem = head_.elem_;
		head_ = head_.next_;
		
		if(head_ == null)
			tail_ = head_ = null;
		--size_;
		
		lock_.unlock();
		return elem;
	}

	@Override
	public void put(T elem) throws InterruptedException {
		lock_.lock();
		Node<T> newNode = new Node<>();
		newNode.elem_ = elem;
		newNode.next_ = null;
		
		if(isEmpty())
			head_ = tail_ = newNode;
		else {
			tail_.next_ = newNode;
			tail_ = tail_.next_;
		}
		
		++size_;
		isNotEmpty_.signal();
		lock_.unlock();
	}

	@Override
	public boolean isEmpty() {
		try {
			lock_.lock();
		} catch (InterruptedException e) {
			throw new IllegalMonitorStateException(e.toString());
		}
		
		boolean res = (size_ == 0);
		lock_.unlock();
		
		return res;
	}
	
	private class Node<T> {
		private T elem_;
		private Node<T> next_;
	}

}
