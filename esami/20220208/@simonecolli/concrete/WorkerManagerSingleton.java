package exam_2022_02_10.concrete;

import exam_2022_02_10.abstracts.Corridor;
import exam_2022_02_10.abstracts.Worker;
import exam_2022_02_10.abstracts.WorkerManager;

public class WorkerManagerSingleton extends WorkerManager{
	private static volatile WorkerManagerSingleton INSTANCE = null;
	
	public static WorkerManagerSingleton getInstance() {
		if(INSTANCE == null)
			synchronized (WorkerManagerSingleton.class) {
				if(INSTANCE == null)
					INSTANCE = new WorkerManagerSingleton();
			}
		return INSTANCE;
	}
	
	private WorkerManagerSingleton(Corridor corridor) {
		super(corridor);
	}
	
	private WorkerManagerSingleton() {
		this(new CorridorImpl());
	}
	
	@Override
	public void createWorker(int n) {
		workers_ = new Worker[n];
		for(int i = 0; i < n; ++i)
			workers_[i] = new WorkerImpl(corridor_);
	}
}
