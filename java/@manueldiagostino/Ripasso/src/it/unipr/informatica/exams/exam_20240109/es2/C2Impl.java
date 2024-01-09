package it.unipr.informatica.exams.exam_20240109.es2;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class C2Impl implements C2 {
	private Object mutexC2;
	
	public C2Impl() {
		this.mutexC2 = new Object();
	}
	
	@Override
	public C3 m(C1[] c) {
		
		C3Impl result = new C3Impl();
		Runnable[] runnables = new Runnable[c.length];
		
		for (int i=0; i<c.length; ++i) {
			C1 target = c[i];
			
			runnables[i] = () -> {
				target.m1();
				
				synchronized (mutexC2) {
					while (!result.isKDone())
						try {
							mutexC2.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				
				target.m2();
			};
		}
		
		synchronized (mutexC2) {
			for (int i=0; i<c.length; ++i) {
				new Thread(runnables[i]).start();
			}
			
			System.out.println("m returning");
			return result;
		}
	}
	
	
	private class C3Impl implements C3 {
		public boolean done;
		public Object mutex;
		
		public C3Impl() {
			this.done = false;
			this.mutex = new Object();
		}
		
		@Override
		public void k() {
			synchronized (mutexC2) {
				synchronized (mutex) {
					done = true;					
				}
				
				System.out.println("k called");
				mutexC2.notifyAll();
			}
		}
		
		public boolean isKDone() {
			synchronized (mutexC2) {
				return done;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		C1[] target = new C1[10];
		for (int i=0; i<10; ++i)  {
			target[i] = new C1() {
				@Override
				public void m1() {
					System.out.println("m1");
				}
				
				@Override
				public void m2() {
					System.out.println("m2");
				}
			};
		}
		
		C2 c2 = new C2Impl();
		C3 res = c2.m(target);
		
		
		Thread.sleep(5000);
		res.k();
	}
}
