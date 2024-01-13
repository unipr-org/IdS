package exam_2024_01_09.es3;

import java.util.Iterator;

public class Tuple<T> implements Iterable<T>{
	private Object[] elements;
	private int size;
	private int capacity;
	
	public Tuple(int n) {
		if(n < 0)
			throw new IllegalMonitorStateException("n < 0");
		
		capacity = n;
		size = 0;
		elements = new Object[n];	
	}
	
	public void add(T elem) throws IllegalStateException {
		
		synchronized (elements) {
			if(size >= capacity)
				throw new IllegalStateException("size >= capacity");
			
			elements[size] = elem;
			++size;
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new InnerIterator<>();
	}
	
	private class InnerIterator<T> implements Iterator<T> {
		private int current;
		private InnerIterator() {
			current = -1;
		}
		
		@Override
		public boolean hasNext() {
			synchronized (elements) {
				if(current + 1 < size)
					return true;
				if(size == capacity)
					return false;
				try {
					elements.wait();
					return true;
				} catch (InterruptedException e) {
					return false;
				}
				
			}
		}

		@Override
		public T next() {
			synchronized (elements) {
				
				if(current == -1 && size == 0)
					throw new IllegalMonitorStateException("current == 0 && size == 0");
				
				@SuppressWarnings("unchecked")
				T currentElem = (T) elements[++current];
				
				
				return currentElem;
			}
		}
		
	}
}
