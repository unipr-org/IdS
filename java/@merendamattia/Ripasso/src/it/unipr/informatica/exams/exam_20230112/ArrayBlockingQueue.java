package it.unipr.informatica.exams.exam_20230112;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;
	private int size;
	private int capacity;
	private Lock lock;
	private Condition isNotEmpty;
	private Condition isNotFull;
	
	public ArrayBlockingQueue() {
		this.queue = new Object[256];
		this.capacity = 256;
		this.size = 0;
		this.lock = new ReentrantLock();
		this.isNotEmpty = lock.newCondition();
		this.isNotFull = lock.newCondition();
	}
	
	@Override
	public T take() throws InterruptedException {
		lock.lock();
		if(isEmpty())
			isNotEmpty.await();
		
		@SuppressWarnings("unchecked")
		T result = (T) queue[0];
		
		Object[] newQueue = new Object[capacity];
		
		for(int i = 1; i < size; i++)
			newQueue[i - 1] = queue[i];
		
		--size;
		queue = newQueue;
		System.out.println(size);
		
		isNotFull.signal();
		lock.unlock();
		
		return result;
	}

	@Override
	public void put(T item) throws InterruptedException {
		lock.lock();
		
		if(size == capacity)
			isNotFull.await();
		
		queue[size] = item;
		++size;
		
		isNotEmpty.signal();
		
		lock.unlock();
	}

	@Override
	public boolean isEmpty() {
		lock.lock();
		boolean result = size == 0;
		lock.unlock();
		return result;
	}

} // ! ArrayBlockingQueue<T>
