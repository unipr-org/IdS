
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

## AOP
### Shared
```java
public class SharedAspect {
	public static <T> T attach(T target) {
		// ...
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		return (T) proxy;
	}

	private static class InnerInvocationHandler implements InvocationHandler { /* ... */ }
}
```

---

### Logging
```java
public class LoggingAspect {
	public static <T> T attach(T target) {
		// ...
		Class<?> clazz = target.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		Object proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InnerInvocationHandler(target));
		return (T) proxy;
	}

private static class InnerInvocationHandler implements InvocationHandler {
	private Object target;
	private Object mutex;
	private Thread currentThread;
	
	public InnerInvocationHandler(Object target){ /* ... */ }
	
	private void log(String msg) {
		synchronized (mutex) {
			Timestamp now = Timestamp.from(Instant.now());
			// ...
		}
	}
	//...
}
```

---

### Persistent
```java
public interface PersistentHandler<T extends Serializable> {
	public T get();
	public void commit() throws IOException;
	public void rollback() throws IOException;
}
```
```java
public class PersistentAspect {
	public static <T extends Serializable> PersistentHandler<T> attach(String fileName, T target) throws IOException {
		return attach(new File(fileName), target);
	}

	public static <T extends Serializable> PersistentHandler<T> attach(File file, T target) throws IOException {
		return new InnerPersistentHandler<T>(file, target);
	}

	private static class InnerPersistentHandler<T extends Serializable> implements PersistentHandler<T> {
		private File file;
		private T target;

		public InnerPersistentHandler(File file, T target) throws IOException {
			if(file == null)
				throw new IllegalArgumentException("file == null");
			this.file = file;
			if(target == null)
				throw new IllegalArgumentException("target == null");
			if(file.exists() && !file.isFile())
				throw new IllegalArgumentException("file.exists() && !file.isFile()");
			if(file.exists())
				load();
			else
				this.target = target;
		}

		@Override
		public T get() { return target; }
		@Override
		public void commit() throws IOException { save(); }
		@Override
		public void rollback() throws IOException { load(); }

		// Carica le informazioni dal file deserializzando l'oggetto
		private void load() throws IOException {
			try ( InputStream inputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
			){
				target = (T) objectInputStream.readObject();
				if(target == null)
					throw new IOException("target == null");
			} catch (IOException e) {}	
				throw e;	
			} catch (Throwable e) {
				throw new IOException(e);
			}
		}
	
		// Scrive le informazioni sul file serializzando l'oggetto
		private void save() throws IOException {
			try ( OutputStream outputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
			){
				objectOutputStream.writeObject(target);
			} catch (IOException e) {
				throw e;
			} catch (Throwable e) {
				throw new IOException(e);
			}
		}
	} // ! InnerPersistentHandler
} // ! PersistentAspect
```

---

### Other
#### Active
#### Remote
#### Relodable

## UML
### Class diagram

## Design Patterns
### Creational
### Structural
### Behavioral
