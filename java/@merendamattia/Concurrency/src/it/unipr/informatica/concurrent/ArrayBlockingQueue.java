package it.unipr.informatica.concurrent;

import it.unipr.informatica.concurrent.locks.Condition;
import it.unipr.informatica.concurrent.locks.Lock;
import it.unipr.informatica.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;

	private int in, out;

	private int count, size;

	private Lock lock;

	private Condition isNotEmpty, isNotFull;

	public ArrayBlockingQueue(int size) {
		if (size < 1)
			throw new IllegalArgumentException("size < 1");

		this.queue = new Object[size];

		this.in = 0;
		this.out = 0;

		this.count = 0;
		this.size = size;
		
		this.lock = new ReentrantLock();

		this.isNotEmpty = lock.newCondition();

		this.isNotFull = lock.newCondition();
	}

	@Override
	public void put(T object) throws InterruptedException {
		if (object == null)
			throw new NullPointerException("object == null");

		try {
			lock.lock();

			while (count == size)
				isNotFull.await(); // Se la coda e' piena, mi metto in attesa della condizione

			queue[in] = object;

			++count;

			in = (in + 1) % size; // Sfrutto l'effetto di coda circolare

			isNotEmpty.signal(); // Segnalo che la coda non e' vuota
		} finally {
			lock.unlock();
		}
	}

	@Override
	public T take() throws InterruptedException {
		try {
			lock.lock();

			while (count == 0)
				isNotEmpty.await(); // Se la coda e' vuota aspetto

			@SuppressWarnings("unchecked")
			// è un'annotazione in Java che indica al compilatore di ignorare gli avvisi 
			// relativi all'uso non sicuro di operazioni di tipo generico all'interno 
			// di un determinato blocco di codice o metodo.
			
			T result = (T) queue[out];

			queue[out] = null;

			--count;

			out = (out + 1) % size;

			isNotFull.signal();

			return result;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void clear() {
		lock.lock();

		in = out = count = 0;

		queue = new Object[size];

		isNotFull.signalAll();

		lock.unlock();
	}

	@Override
	public int remainingCapacity() {
		lock.lock();

		int result = size - count;

		lock.unlock();

		return result;
	}

	@Override
	public boolean isEmpty() {
		lock.lock();

		boolean result = (count == 0);

		lock.unlock();

		return result;
	}
	
	@Override 
	public void printQueue() {
		// TODO: to implement
	}
}
