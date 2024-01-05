package it.unipr.informatica.blockingQueue;

import it.unipr.informatica.locks.Condition;
import it.unipr.informatica.locks.Lock;
import it.unipr.informatica.locks.ReentrantLock;

public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue_;
	private int capacity_;
	private int out_; // firs elem that can be read
	private int in_; // first free slot
	private int count_;

	private Lock lock_;
	private Condition isNotEmpty_;
	private Condition isNotFull_;

	public ArrayBlockingQueue(int capacity) {
		if (capacity < 1 || capacity > Integer.MAX_VALUE)
			throw new IllegalArgumentException("Illegal capacity");

		capacity_ = capacity;
		out_ = -1;
		in_ = 0;
		count_ = 0;
		queue_ = new Object[capacity];

		lock_ = new ReentrantLock();
		isNotEmpty_ = lock_.NewCondition();
		isNotFull_ = lock_.NewCondition();
	}

	@Override
	public void put(T object) throws InterruptedException {
		try {
			lock_.lock();
			while (out_ == in_) {
				System.out.println("isFull");
				isNotFull_.await();
			}

			queue_[in_] = object;
			++count_;
			if (out_ == -1)
				out_ = in_;

			in_ = ((in_ + 1) % capacity_);

			if (in_ == (out_ + 1) % capacity_)
				isNotEmpty_.signalAll();

//			System.out.println("put: " + object);
		} finally {
			lock_.unlock();
		}
	}

	@Override
	public T take() throws InterruptedException {
		try {
			lock_.lock();

			while (out_ == -1) {
//				System.out.println("isEmpty");
				isNotEmpty_.await();
			}

			@SuppressWarnings("unchecked")
			T result = (T) queue_[out_];
			out_ = ((out_ + 1) % capacity_);
			--count_;

			if (out_ == in_)
				out_ = -1;

			if (out_ == (in_ + 1) % capacity_)
				isNotFull_.signalAll();

//			System.out.println("take: " + result);			
			return result;
		} finally {
			lock_.unlock();
		}
	}

	@Override
	public void clear() {
		lock_.lock();

		out_ = -1;
		in_ = 0;
		count_ = 0;
		isNotFull_.signalAll();

		lock_.unlock();

//		System.out.println("cleared");
	}

	@Override
	public boolean isEmpty() {
		boolean result;
		lock_.lock();
		result = (out_ == -1);
		lock_.unlock();

		return result;
	}

	@Override
	public boolean isFull() {
		boolean result;
		lock_.lock();
		result = (out_ == in_);
		lock_.unlock();

		return result;
	}

	@Override
	public int remainingCapacity() {
		int result;

		lock_.lock();

		if (out_ == -1)
			result = capacity_;
		else if (in_ > out_)
			result = capacity_ - (in_ - out_);
		else
			result = capacity_ - (capacity_ - out_) - (in_);
		// result = (capacity_ - (last_ - first_ + 1))%capacity_;

		lock_.unlock();

		return result;
	}

	@Override
	public void print() {
		lock_.lock();
		System.out.print("[");

		int numElem = capacity_ - remainingCapacity();
		for (int i = out_; numElem > 0; ++i, --numElem) {
			System.out.print(queue_[i % capacity_]);

			if (numElem != 1)
				System.out.print(",");
		}

		System.out.println("]");
		lock_.unlock();
	}

	public int getSize() {
		int result;

		lock_.lock();
		result = count_;
		lock_.unlock();

		return result;
	}
}
