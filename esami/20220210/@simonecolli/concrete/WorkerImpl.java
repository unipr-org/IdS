package exam_2022_02_10.concrete;

import exam_2022_02_10.abstracts.Corridor;
import exam_2022_02_10.abstracts.Worker;

public class WorkerImpl implements Worker{

	private Corridor corridor_;
	
	public WorkerImpl(Corridor corridor) {
		corridor_ = corridor;
	}
	@Override
	public void execute() {
		corridor_.enter();
		System.out.println("In corridor " + Thread.currentThread().getId());
		corridor_.exit();
	}

	@Override
	public Corridor getCorridor() {
		return corridor_;
	}

}
