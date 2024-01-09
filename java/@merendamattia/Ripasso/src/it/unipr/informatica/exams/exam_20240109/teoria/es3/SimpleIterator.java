package it.unipr.informatica.exams.exam_20240109.teoria.es3;

public class SimpleIterator<T> implements Iterator<T> {
	private Tuple<T> obj;
	private int current;
	
	public SimpleIterator(Tuple<T> item) {
		this.obj = item;
		this.current = 0;
	}
	
	@Override
	public boolean hasNext() {
		try {
			synchronized (obj) {
				if(current == obj.size() && obj.remainingCapacity() > 0)
					obj.wait();
			}
		} catch (InterruptedException e) {
			return false;
		}
		
		if(current == obj.size() && obj.remainingCapacity() == 0)
			return false;
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		return (T) obj.getItem(current++);
	}

} // ! SimpleIterator<T>
