package it.unipr.informatica.exams.exam_20230207.lab;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;
	private int capacity;
	private int size;
	private Object mutex;
	
	public ArrayBlockingQueue() { this(256); }
	public ArrayBlockingQueue(int capacity) {
		if(capacity < 1 || size > Integer.MAX_VALUE)
			throw new IllegalArgumentException("size < 1 || size > Integer.MAX_VALUE");
		this.capacity = capacity;
		this.mutex = new Object();
		this.queue = new Object[capacity];
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T take() {
		T result = null;
		synchronized (mutex) {
			try {
				while(size == 0)
					mutex.wait();
			} catch (InterruptedException e) {
				System.err.println(e.getCause());
			}
			
			result = (T) queue[0];
			for(int i = 0; i < size - 1; i++)
				queue[i] = queue[i + 1];
			
			queue[size - 1] = null;
		
			if(size == capacity)
				mutex.notifyAll();
			
			--size;
		}
		
		return result;
	}

	@Override
	public void put(T item) {
		synchronized (mutex) {
			try {
				while(size == capacity)
					mutex.wait();
			} catch (InterruptedException e) {
				System.err.println(e.getCause());
			}
			
			queue[size] = item;
			size++;
			
			if(size == 1)
				mutex.notifyAll();
		}
	}

	@Override
	public int remainingCapacity() {
		synchronized (mutex) {
			return capacity - size;
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (mutex) {
			return size == 0;
		}
	}
	
	@Override
	public int size() {
		synchronized (mutex) {
			return size;
		}
	}
	
	@Override
	public String toString() {
		synchronized (mutex) {
			String res = "[";
			for(int i = 0; i < size - 1; i++)
				res += queue[i] + ", ";
			res += queue[size - 1] + "]";
			return res;
		}
	}
	
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>();
		q.put(1);
		q.put(2);
		q.put(3);
		System.out.println(q);
		q.take();
		q.put(4);
		System.out.println(q);
		q.take();
		q.take();
		q.put(5);
		System.out.println(q);
	}

} // ! ArrayBlockingQueue
