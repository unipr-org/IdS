package it.unipr.informatica.pools;

import static org.junit.Assert.*;
import org.junit.Test;

import it.unipr.informatica.executors.*;
import it.unipr.informatica.executors.model.Callable;
import it.unipr.informatica.executors.model.Callback;
import it.unipr.informatica.executors.model.Future;
import it.unipr.informatica.pools.model.ExecutorService;

public class SimpleThreadPoolTest {

	@Test(expected = IllegalArgumentException.class)
	public void testZeroWorkers() {
		ExecutorService es = Executors.newThreadPool(0);
		es.shutdown();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExecuteWithRunnableNull() {
		ExecutorService es = Executors.newThreadPool();
		es.execute(null);
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testExecuteWithShutdownTrue() {
		ExecutorService es = Executors.newThreadPool();
		es.shutdown();
		es.execute(() -> {
			System.out.println("testExecuteWithShutdownTrue");
		});
	}
	
	@Test
	public void testSubmitReturnsFutureValueWithRunnable() {
		ExecutorService es = Executors.newThreadPool();
		
		Future<?> result = es.submit(() -> {
			System.out.println("testSubmitReturnsFutureValueWithRunnable");
		});
		
		try {
			assertEquals(result.get(), null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitReturnsFutureValueWithRunnableNull() {
		ExecutorService es = Executors.newThreadPool();
		es.submit((Runnable) null);
	}
	
	@Test
	public void testSubmitReturnsFutureValueWithCallable() {
		ExecutorService es = Executors.newThreadPool();
		
		Future<?> result = es.submit(() -> {
			System.out.println("testSubmitReturnsFutureValueWithCallable");
			return 10;
		});
		
		try {
			assertEquals(result.get(), 10);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitReturnsFutureValueWithCallableNull() {
		ExecutorService es = Executors.newThreadPool();
		es.submit((Callable) null);
	}
	
	@Test
	public void testSubmitWithRunnableAndCallback() {
		ExecutorService es = Executors.newThreadPool();
		
		Runnable runnable = () -> {
			System.out.println("testSubmitWithRunnableAndCallback");
		};
		
		Callback<?> callback = (result) -> {
			System.out.println("testSubmitWithRunnableAndCallback " + result);
		};
		
		es.submit(runnable, callback);
		/*
		 * Output atteso:
		 * testSubmitWithRunnableAndCallback
		 * testSubmitWithRunnableAndCallback null
		 */
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitWithRunnableNullAndCallback() {
		ExecutorService es = Executors.newThreadPool();
		
		Runnable runnable = () -> {
			System.out.println("testSubmitWithRunnableNullAndCallback");
		};
		
		Callback<?> callback = (result) -> {
			System.out.println("testSubmitWithRunnableNullAndCallback " + result);
		};
		
		es.submit((Runnable) null, callback);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitWithRunnableAndCallbackNull() {
		ExecutorService es = Executors.newThreadPool();
		
		Runnable runnable = () -> {
			System.out.println("testSubmitWithRunnableNullAndCallback");
		};
		
		Callback<?> callback = (result) -> {
			System.out.println("testSubmitWithRunnableNullAndCallback " + result);
		};
		
		es.submit(runnable, (Callback) null);
	}
	
	@Test
	public void testSubmitWithCallableAndCallback() {
		ExecutorService es = Executors.newThreadPool();
		
		Callable<Integer> callable = () -> {
			System.out.println("testSubmitWithCallableAndCallback");
			return 10;
		};
		
		Callback<Integer> callback = (result) -> {
			System.out.println("testSubmitWithCallableAndCallback " + result);
		};
		
		es.submit(callable, callback);
		/*
		 * Output atteso:
		 * testSubmitWithCallableAndCallback
		 * testSubmitWithCallableAndCallback 10
		 */
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitWithCallableNullAndCallback() {
		ExecutorService es = Executors.newThreadPool();
		
		Callback<Integer> callback = (result) -> {
			System.out.println("testSubmitWithCallableNullAndCallback " + result);
		};
		
		es.submit((Callable) null, callback);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSubmitWithCallableAndCallbackNull() {
		ExecutorService es = Executors.newThreadPool();
		
		Callable<Integer> callable = () -> {
			System.out.println("testSubmitWithCallableAndCallback");
			return 10;
		};
		
		es.submit(callable, (Callback) null);
	}
	
} // ! SimpleThreadPoolTest
