package it.unipr.informatica.exams.exam_20240109.teoria.es3;

public class Tuple<T> implements Iterable<T> {

	private Object[] array;
	private int size;
	private int capacity;
	
	public Tuple(int n) {
		if(n < 1)
			throw new IllegalArgumentException("n < 1");
		this.array = new Object[n];
		this.capacity = n;
		this.size = 0;
	}
	
	public void add(T item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
		if(size == capacity)
			throw new IllegalStateException("full");
		
		array[size++] = item;
		
		synchronized (this) {
			this.notifyAll();
		}
		
	}
	
	public Object getItem(int i) {
		return array[i];
	}
	
	public int size() {
		return size;
	}
	
	public int remainingCapacity() {
		return capacity - size;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SimpleIterator<T>(this);
	}

} // ! Tuple<T>
