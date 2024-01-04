package exam_2023_02_07.lab.concrete;

import exam_2023_02_07.lab.abstracts.Actor;
import exam_2023_02_07.lab.abstracts.Message;
import exam_2023_02_07.lab.abstracts.Resource;
import exam_2023_02_07.lab.dispatcher.ActorDispatcher;
import exam_2023_02_07.lab.dispatcher.ResourceDispatcher;

public class ActorImpl implements Actor {
	private Object mutex_;
	private Thread thread_;
	private int id_;
	
	public ActorImpl(int id) {
		mutex_ = new Object();
		thread_ = new InnerCycle();
		this.id_ = id;
		thread_.start();
	}
	
	@Override
	public void deliver(Message message) {
		System.out.println("[actor " + id_ + "] recived message: " + message.getContent());
		if (message.getContent() < 0)
			return;
		
		synchronized (mutex_) {
			Actor a = ActorDispatcher.getInstance().getRandomActor();
			a.deliver(MessageFactory.getInstance().makeMessage(message.getContent()-1));
		}
		
	}
	private class InnerCycle extends Thread {
		
		@Override
		public void run() {
			mainCycle();
		}
		private void mainCycle() {
			while(true) {
				if(!ResourceDispatcher.getInstance().isRunning())								
					break;
				
				int k = (int) (Math.random() * (9 + 1));
				
				try {
					Resource[] resources = new Resource[k];
					
					for(int i = 0; i < k; ++i)
						resources[i] = ResourceDispatcher.getInstance().getResource();
					
					for(int i = 0; i < k; ++i)
						resources[i].acquire();
					
					int sum = 0;
					
					for(int i = 0; i < k; ++i)
						sum += resources[i].use();
					
					try {
						Thread.sleep(sum);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					//			release
					for(int i = 0; i < k; ++i)
						resources[i].release();
					
					synchronized (mutex_) {
						ActorDispatcher.getInstance().getRandomActor().deliver(MessageFactory.getInstance().makeMessage(sum));						
					}
					
				} catch (InterruptedException e) {
					
				}
			}
			System.out.println("interrupting");
			interrupt();
		}
	}
	

}
