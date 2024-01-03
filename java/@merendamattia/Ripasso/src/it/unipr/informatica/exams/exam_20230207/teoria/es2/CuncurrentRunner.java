package it.unipr.informatica.exams.exam_20230207.teoria.es2;

public abstract class CuncurrentRunner {
	private static Object mutex = new Object();
	private static OneReturned flag = new OneReturned();
	private static Object result;
	
	public static Object execute(Task t1, Task t2) throws InterruptedException {
		
		synchronized (flag) {
			result = null;
			
			new Thread(new TaskAdapter(t1, 1)).start();
			new Thread(new TaskAdapter(t2, 2)).start();
			
			flag.wait();
		}
		
		System.out.println("execute(): returning Object " + result);
		return result;
	}
	
	private static class OneReturned {
		private boolean value = false;
	}
	
	private static class TaskAdapter implements Runnable {
		private Task t;
		private Object MyResult;
		private int id;
		
		TaskAdapter(Task t, int id) {
			this.t = t;
			this.id = id;
		}
		
		@Override
		public void run() {
			System.out.println("Task [" + id + "] entered");
			
			MyResult = t.run();
			
			System.out.println("Task [" + id + "] run terminated");
			
			synchronized (mutex) {
				synchronized (flag) {
					if(!flag.value) {
						CuncurrentRunner.flag.value = true;
						CuncurrentRunner.result = MyResult;
						flag.notify();
						
						System.out.println("Task [" + id + "] notify");
					}
					
				}
			}
		}
	} // ! TaskAdapter
}
