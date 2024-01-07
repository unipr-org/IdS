package it.unipr.informatica.pools;

import it.unipr.informatica.executors.Callable;
import it.unipr.informatica.executors.Callback;
import it.unipr.informatica.executors.Future;
import it.unipr.informatica.executors.SimpleFuture;

public class SimpleThreadPoolTest {
	public void testExecute() {
		ExecutorService es = Executors.newThreadPool();

		for (int i = 0; i < 5; ++i) {
			final int id = i;
			Runnable runnable = () -> {
				try {
					Thread.sleep(2000 + (int) (2000 * Math.random()));
				} catch (InterruptedException e) {

				}
				System.out.println("Thread [" + id + "] terminated");
			};

			es.execute(runnable);
		}

		try {
			Thread.sleep(5000);
			es.shutdown();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}

	public void testSubmit_Runnable() {
		ExecutorService es = Executors.newThreadPool();

		Future<Void> future = es.submit(() -> {
			try {
				Thread.sleep(3000 + (int) (2000 * Math.random()));
				System.out.println("task terminated");
			} catch (InterruptedException e) {
				System.out.println("task interrupted");
			}
		});

		try {
			Thread.sleep(5000);
			future.get();
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Runnable interrupted");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}

		Future<Void> future1 = es.submit(() -> {
			try {
				Thread.sleep(3000 + (int) (2000 * Math.random()));
				throw new IllegalArgumentException("task: IllegalArgument");
			} catch (InterruptedException e) {
				System.out.println("task interrupted");
			}
		});

		try {
			Thread.sleep(5000);
			future1.get();
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Runnable interrupted");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

	public void testSubmit_Runnable_Callback() {
		ExecutorService es = Executors.newThreadPool();

		Callback<Void> callback = new Callback<Void>() {
			@Override
			public void onSuccess(Void arg) {
				System.out.println("task terminated");
			}

			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}
		};

		Runnable runnable = () -> {
			try {
				Thread.sleep(3000 + (int) (2000 * Math.random()));
				throw new IllegalArgumentException("task: IllegalArgument");
			} catch (InterruptedException e) {
			}
		};

		es.submit(runnable, callback);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Runnable interrupted");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		es.submit(() -> {
			try {
				Thread.sleep(3000 + (int) (2000 * Math.random()));
			} catch (InterruptedException e) {
			}
		}, callback);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Runnable interrupted");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

	public void testSubmit_Callable() {
		ExecutorService es = Executors.newThreadPool();
		Future<Integer> future = null;

		Callable<Integer> callable = () -> {
			return 7;
		};

		future = es.submit(callable);
		try {
			System.out.println(future.get());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Callable<Integer> callable1 = () -> {
			throw new Exception("callable1");
		};

		future = es.submit(callable1);
		try {
			Integer res = future.get();
			System.out.println(res);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

	public void testSubmit_Callable_Callback() {
		ExecutorService es = Executors.newThreadPool();

		Callable<Integer> callable = () -> {
			return 7 * 7;
		};

		Callback<Integer> callback = (Integer i) -> {
			System.out.println(i);
		};

		es.submit(callable, callback);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Callable_Callback interrupted");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Callable<Integer> callable1 = () -> {
			throw new Exception("callable1");
		};

		es.submit(callable1, callback);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("testSubmit_Callable_Callback interrupted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
	}

	public static void main(String[] args) {
		SimpleThreadPoolTest test = new SimpleThreadPoolTest();
//		test.testExecute();
//		test.testSubmit_Runnable(); 
//		test.testSubmit_Runnable_Callback(); 
//		test.testSubmit_Callable();
		test.testSubmit_Callable_Callback();
	}
}
