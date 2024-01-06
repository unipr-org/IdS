package it.unipr.informatica.exams.exam_20230207.preTest;

public abstract class ConcurrentRunner {
	private static OneReturned flag_ = new OneReturned();
	private static Object mutex_ = new Object();
	private static Object result_;

	public static Object execute(Task t1, Task t2) throws InterruptedException {
		synchronized (flag_) {
			result_ = null;

			new Thread(new TaskAdapter(t1, 1)).start();
			new Thread(new TaskAdapter(t2, 2)).start();

			flag_.wait();
		}

		System.out.println("execute() returning Object: " + result_);
		return result_;
	}

	private static class OneReturned {
		private boolean value_ = false;
	}

	private static class TaskAdapter implements Runnable {
		Task task_;
		int id_;

		private TaskAdapter(Task t, int id) {
			task_ = t;
			id_ = id;
		}

		@Override
		public void run() {
			System.out.println("Task [" + id_ + "] entered");
			Object result = task_.run();
			System.out.println("Task [" + id_ + "] run terminated");

			synchronized (mutex_) {
				synchronized (flag_) {

					if (flag_.value_ == true)
						return;

					ConcurrentRunner.flag_.value_ = true;
					ConcurrentRunner.result_ = result;
					flag_.notify();
					System.out.println("Task [" + id_ + "] notified");
				}
			}
		}
	}
}
