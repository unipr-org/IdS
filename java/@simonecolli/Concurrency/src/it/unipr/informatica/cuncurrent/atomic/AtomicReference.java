package it.unipr.informatica.cuncurrent.atomic;

import java.util.function.UnaryOperator;

public class AtomicReference <T>{
	private T value_;
	private Object lock_;
	
	public AtomicReference() {
//		va ad invocare un costruttore diverso dal corrente con il valore passato
		this(null);
	}
	
	public AtomicReference(T value) {
		this.value_ = value;
		this.lock_ = new Object();
	}
	public T get() {
		synchronized (lock_) {
			return value_;
		}
	}
	public void set(T value) {
		synchronized (lock_) {
			this.value_ = value;
		}
	}
//	per rendere atomica la get e la set in modo contemporaneo
	public T getAndSet(T value) {
		synchronized (lock_) {
			T tmp = this.value_;
			this.value_ = value;
			return tmp;
		}
	}
//	funzione che dato il vecchio valore calcoli quello nuovo e ritorna il vecchio
	public T getAndUpdate(UnaryOperator<T> update) {
		synchronized (lock_) {
			T tmp = value_;
			
//			dato l'oggetto calcola la funzione (applica la funzione)
			this.value_ = update.apply(this.value_);
			
			return tmp;
		}
	}
	
}
