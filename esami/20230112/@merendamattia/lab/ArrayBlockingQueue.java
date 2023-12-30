package it.unipr.informatica.exams.exam_20230112.lab;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;
	private int size;
	private int capacity;
	
	public ArrayBlockingQueue() {
		this.queue = new Object[256];
		this.capacity = 256;
		this.size = 0;
		
//		for(int i = 0; i < capacity; i++)
//			queue[i] = new Object();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T take() throws InterruptedException {
		T result;
		
		synchronized (queue) {
			while(size == 0)
				queue.wait();
			
			
			result = (T) queue[0];
			
			for(int i = 0; i < size - 1; i++)
				queue[i] = queue[i + 1];
			
			queue[size - 1] = null;
			--size;
			
			
			if(size == 0)
				queue.notifyAll();
		}
		
		return result;
	}

	@Override
	public void put(T item) throws InterruptedException {
		synchronized (queue) {
			while(size == capacity)
				queue.wait();
			
			queue[size] = item;
			++size;
			
			if(size == 1)
				queue.notify();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (queue) {
			return size == 0;
		}
	}
	
	@Override
	public String toString() {
		String res = "[";
		for (int i = 0; i < size - 1; i++)
			res += queue[i] + ", ";
			
			
		res += "" + queue[size - 1];
		res += "]";
		
		return res;
	}
	
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>();
		queue.put(1);
		queue.put(2);
		queue.put(3);
		System.out.println(queue);
		
		queue.take();
		queue.put(4);
		System.out.println(queue);
		queue.take();
		queue.take();
		queue.put(5);

		System.out.println(queue);
	}

} // ! ArrayBlockingQueue<T>
