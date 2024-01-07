package it.unipr.informatica.blocking_queue;

import it.unipr.informatica.locks.ReentrantLock;
import it.unipr.informatica.locks.model.Condition;
import it.unipr.informatica.locks.model.Lock;
import it.unipr.informatica.blocking_queue.model.BlockingQueue;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;
	private int size;
	private int capacity;
	private Lock lock;
	private Condition isNotEmpty;
	private Condition isNotFull;
	
	public ArrayBlockingQueue(int maxCapacity){
		if(maxCapacity > Integer.MAX_VALUE)
			throw new IllegalArgumentException("maxCapacity > Integer.MAX_VALUE");
		if(maxCapacity < 1)
			throw new IllegalArgumentException("maxCapacity < 1");
		
		this.capacity = maxCapacity;
		this.size = 0;
		this.queue = new Object[maxCapacity];
		this.lock = new ReentrantLock();
		this.isNotEmpty = lock.newCondition();
		this.isNotFull = lock.newCondition();
	}

	@Override
	public T take() throws InterruptedException {
		lock.lock();
		
		while (size == 0)
			isNotEmpty.await();

		
		@SuppressWarnings("unchecked")
		T result = (T) queue[0];
		
		for(int i = 0; i < size - 1; ++i)
			queue[i] = queue[i + 1];
		queue[size - 1] = null;
		
		--size;
		
		isNotFull.signal();
		lock.unlock();
		
		return result;
	}

	@Override
	public void put(T value) throws InterruptedException {
		lock.lock();

		while (size == capacity)
			isNotFull.await();

		
		queue[size] = value;
		++size;
		
		isNotEmpty.signal();
		lock.unlock();
	}

	@Override
	public void clear() {
		lock.lock();
		
		for(int i = 0; i < capacity; ++i)
			queue[i] = null;
		
		size = 0;
		
		isNotFull.signalAll();
		lock.unlock();
	}

	@Override
	public boolean isEmpty() {
		lock.lock();
		
		boolean result = (size == 0);
		
		lock.unlock();
		
		return result;
	}

	@Override
	public boolean isFull() {
		lock.lock();
		
		boolean result = (size == capacity);
		
		lock.unlock();
		
		return result;
	}

	@Override
	public int remainingCapacity() {
		lock.lock();
		
		int result = capacity - size;
		
		lock.unlock();
		
		return result;
	}

	@Override
	public void print() {
		lock.lock();
		System.out.print("[");
		
		for(int i = 0; i < size; ++i) {
			System.out.print(queue[i]);
			
			if((i + 1) != size)
				System.out.print(", ");
		}
			
		System.out.println("]");
		lock.unlock();
	}
	
	@Override
	public String toString() {
		String result = "";
		
		lock.lock();
		
		result += "[";
		
		for(int i = 0; i < size; ++i) {
			result += queue[i];
			
			if((i + 1) != size)
				result += ", ";
		}
		
		result += "]";
		
		lock.unlock();
		
		return result;
	}

}
