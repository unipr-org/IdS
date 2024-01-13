package it.unipr.informatica.aspects.active.queue;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private T[] elements;
	private int in;
	private int out;
	private int size;
	
	private static final int MIN_CAPACITY = 20;
	
	public ArrayBlockingQueue() {
		this(MIN_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayBlockingQueue(int size) {
		if (size<1)
			throw new IllegalArgumentException("size<1");
		
		this.elements = (T[]) new Object[size];
		this.in = 0;
		this.out = -1;
		this.size = 0;
	}
	
	@Override
	public void put(T elem) throws InterruptedException {
		synchronized (elements) {
			while(size == elements.length)
				elements.wait();
			
			elements[in] = elem;
			in = (in+1)%elements.length;
			++size;
			
			if (size == 1) {
				out = in-1;
				elements.notifyAll();
			}
		}
	}

	@Override
	public T take() throws InterruptedException {
		synchronized (elements) {
			while (size == 0)
				elements.wait();
			
			T res = elements[out];
			out = (out+1)%elements.length;
			--size;
			
			if (size == 0) {
				out = -1;
			} else if (size == elements.length-1)
				elements.notifyAll();

			return res;
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (elements) {
			return size==0;
		}
	}
	
	@Override
	public boolean isFull() {
		synchronized (elements) {
			return size==elements.length;
		}
	}

	@Override
	public int size() {
		synchronized (elements) {
			return size;
		}
	}

	@Override
	public int remainingCapacity() {
		synchronized (elements) {
			return elements.length-size;
		}
	}
	
	public void print() {
		System.out.print("{");
		for(int i=out, count=size; count>0; i=(i+1)%elements.length, count--) {
			System.out.print(elements[i]);
			
			if (count != 1)
				System.out.print(", ");
		}
		System.out.println("}");
	}
	
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);
		
		queue.put(0);
		queue.put(1);
		queue.put(2);
		queue.print();
		
		System.out.println(queue.take());
		System.out.println(queue.take());
		queue.print();
		
		queue.put(3);
		queue.put(4);
		queue.put(5);
		queue.put(6);
		queue.print();
		queue.put(7);
		System.out.println(queue.remainingCapacity());
	}
}
