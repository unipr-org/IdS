package it.unipr.informatica.concurrent.locks;

public class TestLinkedBlockingQueue {
	public void go() {
		BlockingQueue<Integer> list = new LinkedBlockingQueue<Integer>();
		new Thread(() -> {
				try {
					for (int i=0; i<10; ++i)	
						list.put(i*i);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		).start();
		
		new Thread(() -> {
			try {
				list.take();
				list.take();
				list.take();
				list.take();
				list.take();
				list.take();
				list.take();
				list.clear();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	).start();
	}
	
	public static void main(String[] args) {
		new TestLinkedBlockingQueue().go();
	}
}
