package exam_2024_01_09.es2;

public class C2Impl implements C2 {

	@Override
	public C3 m(C1[] c) {
		C3Impl c3 = new C3Impl();
		
		for(int i = 0;  i < c.length; ++i) {
			int tmp = i;
			new Thread(()->{
				c[tmp].m1();
				System.out.println("thread " + tmp + " wait");
				try {
					c3.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				c[tmp].m2();
				
			}).start();
		}
		return c3;
	}
	
	private class C3Impl implements C3 {
		private boolean done;
		private Object mutex;
		private C3Impl() {
			done = false;
			mutex = new Object();
		}
		
		@Override
		public void k() {
			System.out.println("C3Imple k()");
			signalAll();
		}
		private void await() throws InterruptedException {
			synchronized (mutex) {
				if(!done)
					mutex.wait();
			}
		}
		private void signalAll() {
			synchronized(mutex) {
				if(!done) {
					done = true;
					mutex.notifyAll();
				}
			}
		}
		
	}
}
