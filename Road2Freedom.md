
```table-of-contents
```
---
# Argomenti principali

## Concorrenza
### Blocking queue
```java
public interface BlockingQueue<T> {
	public T take();
	public void put(T value);
	public void clear();
	public boolean isEmpty();
	public boolean isFull();
	public int remainingCapacity();
	public void print();
}
```

---

### Locks & Conditions
```java
public interface Condition {
	public void await() throws InterruptedException;
	public void signal();
	public void signalAll();
}
```
```java
public interface Lock {
	public void lock();
	public void unlock();
	public Condition newCondition();
}
```

#### Reentrant Lock
```java
public class ReentrantLock implements Lock {
	private Thread owner;
	private Object mutex;
	private int counter;
	// ...
	
	private class InnerCondition implements Condition {
		private Object cond;
		// ...
	}
}
```

#### Array blocking queue
```java
public class ArrayBlockingQueue<T> implements BlockingQueue<T> {
	private Object[] queue;
	private int size;
	private int capacity;
	private Lock lock;
	private Condition isNotEmpty;
	private Condition isNotFull;
	// ...
}
```

---

### Atomic References
```java
public class AtomicReference<T> {
	private T value;
	private Object lock;

	public AtomicReference() { }
	public AtomicReference(T value) { }
	public T get() { }
	public void set(T value) { }
	public T getAndSet(T value) { }
	public T getAndUpdate(UnaryOperator<T> update) { }
	public T updateAndGet(UnaryOperator<T> update) { }
}
```

---

### Executors
#### Callable
```java
public interface Callable<T, A> {
	public T call();
	public T call(A arg);
}
```
#### Callbacks
```java
public interface Callback<T> {
	public void onSuccess(T value);
	public default void onFailure(Throwable t) {
		t.printStackTrace();
	}
}
```
#### Futures
```java
public interface Future<T> {
	public T get() throws InterruptedException, Throwable;
	public boolean isDone();
}
```

---

### Thread Pools
```java
public interface Executor {
	public void execute(Runnable runnable);
}
```
```java
public interface ExecutorService extends Executor {
	public void shutdown();
	// Gestisco solo le eccezioni
	public Future<?> submit(Runnable task); 
	// Gestisco sia risultato che eccezioni
	public <T> Future<T> submit(Callable<T> task); 
	// Gestisco solo le eccezioni
	public void submit(Runnable task, Callback<?> callback); 
	// Gestisco sia risultato che eccezioni
	public <T> void submit(Callable<T> task, Callback<T> callback); 
}
```

```java
public class Executors {
	public static ExecutorService newThreadPool(int workers) {
		return new SimpleThreadPool(workers);
	}
	public static ExecutorService newThreadPool() {
		return new SimpleThreadPool();
	}
	private Executors() { /* Vuoto */ }
}
```

```java
public class SimpleThreadPool implements ExecutorService {
	public static final int MAX_LIMIT_TASKS = 20;
	public static final int MAX_WORKERS = 10;
	private Thread[] workers;
	private BlockingQueue<Runnable> tasks;
	private boolean shutdown;
	// ...
	private class Worker extends Thread {
		private int id;
		// ...
	} // ! Worker
} // ! SimpleThreadPool
```

---

## Reflection
### Class Object
### Introspection
### Proxy

## SCM

### Todo
## AOP

### Shared
### Logging
### Persistent
### Other

#### Active
#### Remote
#### Relodable

## UML

### Class diagram

## Design Patterns

### Creational
### Todo


---