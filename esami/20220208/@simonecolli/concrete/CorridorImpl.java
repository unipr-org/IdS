package exam_2022_02_10.concrete;

import java.util.Vector;

import exam_2022_02_10.abstracts.Corridor;

public class CorridorImpl implements Corridor {
	public final static int K = 5;

	private int counter_;
	private Object mutex_;
	private Vector<Thread> waiters_;
	private int toRemove_;
	public CorridorImpl() {
		counter_ = 0;
		mutex_ = new Object();
		waiters_ = new Vector<>();
		toRemove_ = 0;
	}
	
	@Override
	public void enter() {
				
		synchronized (mutex_) {
//			System.out.println("Someone is entering... " + Thread.currentThread().getId());
			++counter_;			
			waiters_.add(Thread.currentThread());

			if(counter_ >= K) {
//				K - 1 perché il quinto worker che triggera l'if
//				non viene bloccato, quindi vengono "rilasciati"
//				4 workers
				
				mutex_.notifyAll();
				toRemove_ = 4;
				
				counter_ -= K;
			
			} else {
				try {
//					System.out.println("Someone will wait... " + Thread.currentThread().getId());
					while(toRemove_ == 0)
						mutex_.wait();
					
					--toRemove_;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				System.out.println("Someone will not wait... " + Thread.currentThread().getId());						
			}
				

		}
//		System.out.println("Someone is quitting... " + Thread.currentThread().getId());
	}

	@Override
	public void exit() {
		synchronized (mutex_) {
			waiters_.remove(Thread.currentThread());
		}
	}
	
}
