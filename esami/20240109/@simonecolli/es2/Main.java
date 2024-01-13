package exam_2024_01_09.es2;


public class Main {
	public static void main(String[] args) {
		C2 c2 = new C2Impl();
		C1[] c = new C1[10];
		for(int i = 0; i < c.length; ++i) {
			int tmp = i;
			c[i] = new C1() {
				
				@Override
				public void m2() {
					System.out.println("C1: " + tmp + " m2()");
				}
				
				@Override
				public void m1() {
					System.out.println("C1: " + tmp + " m1()");
				}
			};
		}
		C3 c3 = c2.m(c);
		try {
			Thread.sleep(4000);
			c3.k();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
