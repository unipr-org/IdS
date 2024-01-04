package exam_2023_02_07.lab.concrete;

import exam_2023_02_07.lab.abstracts.Actor;
import exam_2023_02_07.lab.abstracts.ActorResourceAbstractFactory;
import exam_2023_02_07.lab.abstracts.Resource;

public class ActorResourceFactory implements ActorResourceAbstractFactory{
	
	private int resourceId_;
	private int actorId_;
	public ActorResourceFactory() {
		this.resourceId_ = 0;
		this.actorId_ = 0;
	}
	@Override
	public Actor makeActor() {
		return new ActorImpl(actorId_++);
	}

	@Override
	public Resource makeResource() {
		Resource res = new ResourceImpl(resourceId_++);
//		System.out.println("new res: " + res.getID());
		return res;
	}

}
