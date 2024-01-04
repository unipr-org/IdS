package exam_2022_06_22;

import exam_2022_06_22.abstracts.Barrier;
import exam_2022_06_22.abstracts.Notifier;
import exam_2022_06_22.abstracts.Waiter;
import exam_2022_06_22.concrete.BarrierImpl;
import exam_2022_06_22.concrete.NotifierImpl;
import exam_2022_06_22.concrete.WaiterImpl;

public class Main {
	
	public static void main(String[] args) {
		int N = 50;
		
		Barrier barrier = BarrierImpl.getInstance();
		Waiter waiter = new WaiterImpl();
		
		Notifier[] notifiers = new Notifier[N];
		waiter.start();
		for(int i = 0; i < N; ++i) {
			notifiers[i] = new NotifierImpl();
			barrier.add(notifiers[i].getObject());
			notifiers[i].start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waiter.stop();
		for(int i = 0; i < N; ++i)
			notifiers[i].stop();
		
		
	}
}
