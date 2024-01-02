package exam_2022_02_10.abstracts;

public abstract class WorkerManager {
	protected Corridor corridor_;
	protected Worker[] workers_;

	protected WorkerManager(Corridor corridor) {
		corridor_ = corridor;
		workers_ = null;
	}
	
	public abstract void createWorker(int n);
	public Worker getWorker(int i) {
		return workers_[i];
	}
}
