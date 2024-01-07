package it.unipr.informatica.exams.exam_20230207.lab.factory;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Actor;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ActorsDispatcher;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ResourcesDispatcher;
import it.unipr.informatica.exams.exam_20230207.lab.implementations.ActorDispatcherImpl;
import it.unipr.informatica.exams.exam_20230207.lab.implementations.ActorImpl;
import it.unipr.informatica.exams.exam_20230207.lab.implementations.ResourceDispatcherImpl;
import it.unipr.informatica.exams.exam_20230207.lab.implementations.ResourceImpl;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ActorResourceFactoryImpl implements ActorResourceFactory {
	private ActorsDispatcher actorDispatcher;
	private ResourcesDispatcher resourceDispatcher;

	private static volatile ActorResourceFactoryImpl instance;

	public static ActorResourceFactoryImpl getInstance() {
		if (instance == null) {
			synchronized (ActorResourceFactoryImpl.class) {
				if (instance == null)
					instance = new ActorResourceFactoryImpl();
			}
		}

		return instance;
	}

	private ActorResourceFactoryImpl() {
		actorDispatcher = new ActorDispatcherImpl();
		resourceDispatcher = new ResourceDispatcherImpl();
	}

	@Override
	public Actor[] createActors(int n) {
		if (n < 1)
			throw new IllegalArgumentException("Can not create n<1 actors");

		Actor[] res = new Actor[n];
		for (int i = 0; i < n; ++i)
			res[i] = new ActorImpl("Actor " + i, actorDispatcher, resourceDispatcher);

		actorDispatcher.setActors(res);
		return res;
	}

	@Override
	public Resource[] createResources(int n) {
		if (n < 1)
			throw new IllegalArgumentException("Can not create n<1 resources");

		Resource[] res = new Resource[n];
		for (int i = 0; i < n; ++i)
			res[i] = new ResourceImpl(i);

		resourceDispatcher.setResources(res);
		return res;
	}

}
