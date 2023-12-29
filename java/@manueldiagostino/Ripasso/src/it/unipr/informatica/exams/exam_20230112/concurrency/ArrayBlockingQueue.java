package it.unipr.informatica.exams.exam_20230112.concurrency;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private T[] queue;
	private int in; 
	private int out; // vuota se out == -1, piena se in == out
	private int size;
	
	public static final int MAX_CAPACITY = 10000;
	
	@SuppressWarnings("unchecked")
	public ArrayBlockingQueue(int capacity) {
		if (capacity < 1 || capacity > MAX_CAPACITY)
			throw new IllegalArgumentException("Invalid size");
		
		this.queue =  (T[]) new Object[capacity];
		for (int i=0; i<capacity; ++i)
			queue[i] = (T) new Object();
		
		
		this.in = 0;
		this.out = -1;
		this.size = 0;
	}
	
	@Override
	public void put(T elem) throws InterruptedException {
		synchronized (queue) {
			while (size == queue.length)
				queue.wait();
			
			queue[in] = elem;
			in = (in+1)%queue.length;
			++size;
			
			if (size == 1) {
				out = (in-1)%queue.length;
				out = (out<0) ? out+queue.length : out;
				// System.out.println("in: " + in + "\nout: " + out);
				queue.notifyAll();
			}
		}
	}

	@Override
	public T take() throws InterruptedException {
		synchronized (queue) {
			while (size == 0)
				queue.wait();
				
			
			T result = queue[out];
			out = (out+1)%queue.length;
			--size;
			
			if (size == 0) { // coda vuota
				out = -1;
				queue.notifyAll();
			}
			
			return result;
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (queue) {			
			return size == 0;
		}
	}

	@Override
	public boolean isFull() {
		synchronized (queue) {			
			return size == queue.length;
		}
	}

	@Override
	public int remainingCapacity() {
		synchronized (queue) {			
			return queue.length - size;
		}
	}

	@Override
	public int size() {
		synchronized (queue) {
			return this.size;
		}
	}
	
	@Override
	public String toString() {
		
		String res = "[";
		for (int i=out, remainingElem=size; remainingElem>0; --remainingElem, i=(i+1)%queue.length) {
			res += queue[i];
			
			if (remainingElem != 1)
				res += ", ";
		}
		res += "]";
		
		return res;
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
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
}
