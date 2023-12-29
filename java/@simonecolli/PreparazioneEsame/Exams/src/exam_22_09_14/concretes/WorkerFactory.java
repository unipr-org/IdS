package exam_22_09_14.concretes;

import exam_22_09_14.abstracts.Worker;
import exam_22_09_14.abstracts.WorkerAbstractFactory;

public class WorkerFactory implements WorkerAbstractFactory{
	private int workerId_;
	public WorkerFactory() {
		workerId_ = 0;
	}
	@Override
	public Worker makeWorker() {
		return new WorkerImpl(workerId_++);
	}

}
