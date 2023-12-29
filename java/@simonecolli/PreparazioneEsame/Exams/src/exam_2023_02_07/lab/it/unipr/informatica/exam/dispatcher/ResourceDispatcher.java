package exam_2023_02_07.lab.it.unipr.informatica.exam.dispatcher;


import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Condition;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.abstracts.Lock;
import exam_2023_01_12.lab.it.unipr.informatica.exam.concurrency.concrete.ReentrantLock;
import exam_2023_02_07.lab.it.unipr.informatica.exam.abstracts.Resource;

public class ResourceDispatcher {
	private volatile static ResourceDispatcher INSTANCE = null;
	
	public static ResourceDispatcher getInstance() {
		if(INSTANCE == null)
			synchronized (ResourceDispatcher.class) {
				if(INSTANCE == null)
					INSTANCE = new ResourceDispatcher();
			}
		return INSTANCE;
	}
	
	
	private Node head_, tail_;
	private Lock lock_;
	private Condition missingResources_;
	private int size_;
	private volatile boolean shutdown_;
	private Object mutex_;
	
	private ResourceDispatcher() {
		this.lock_ = new ReentrantLock();
		this.missingResources_ = lock_.newCondition();
		head_ = tail_ = null;
		size_ = 0;
		mutex_ = new Object();
		shutdown_ = false;
	}
	
	public void addResource(Resource r) {
		try {
			lock_.lock();
			Node newNode = new Node();
			newNode.res = r;
			newNode.next_ = null;
			
			if(head_ == null)
				head_ = tail_ = newNode;
			else {
				tail_.next_ = newNode;
				tail_ = tail_.next_;
			}
			++size_;
			System.out.println("resource added " + tail_.res.getID());
			missingResources_.signal();
			lock_.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public Resource getResource() throws InterruptedException {
		
		lock_.lock();
		
		while(size_ == 0)
			missingResources_.await();
		
		Resource res = head_.res;
		head_ = head_.next_;
		
		System.out.println("resource: " + res.getID() + " aquired");
		
		--size_;
		
		if(head_ == null)
			head_ = tail_ = null;
		
		lock_.unlock();
		
		return res;
	}
	
	public void shoutdown() {
		synchronized (mutex_) {
			this.shutdown_ = true;			
		}
	}
	
	public boolean isRunning() {
		synchronized (mutex_) {
			return !shutdown_;			
		}
	}
	
	private class Node {
		Resource res;
		Node next_;
	}
}
