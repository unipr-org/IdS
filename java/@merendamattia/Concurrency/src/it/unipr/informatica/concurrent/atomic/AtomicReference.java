package it.unipr.informatica.concurrent.atomic;
import java.util.function.UnaryOperator;

public class AtomicReference<T> {
	private T value;
	private Object lock;
	
	public AtomicReference() {
		this(null);
	}
	
	public AtomicReference(T value) {
		this.value = value;
		this.lock = new Object();
	}
	
	public T get() {
		synchronized (lock) {
			return value;
		}
	}
	
	public void set(T value) {
		synchronized (lock) {
			this.value = value;
		}
	}
	
	public T getAndSet(T value) {
		synchronized (lock) {
			T result = this.value;
			this.value = value;
			return result;
		}
	}
	
	public T getAndUpdate(UnaryOperator<T> update) {
		synchronized (lock) {
			T result = this.value;
			this.value = value;
			return result;
		}
	}
}
