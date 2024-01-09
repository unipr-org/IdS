package it.unipr.informatica.exams.exam_20240109.teoria.es2;

public class C2Impl implements C2 {

	@Override
	public C3 m(C1[] c) {
		C3 o = new C3() {

			@Override
			public void k() {
				System.out.println("do something");
				
				synchronized (this) {
					this.notifyAll();
				}
				
			}
			
		};
		
		for(int i = 0; i < c.length; i++) {
			C1 x = c[i];
						
			new Thread(() -> {
				x.m1();
				
				synchronized (o) {
					try {
						o.wait();
					} catch (InterruptedException e) {
						System.err.println(e.getCause());
					}
				}
				
				x.m2();
			}).start();
		}
		
		return o;
	} // ! m()
	
	public static void main(String[] args) {
		C1[] target = new C1[10];
		
		for (int i = 0; i < 10; ++i)  {
			
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
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.k();
	
	} // ! main()
} // ! C2Impl
