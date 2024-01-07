package it.unipr.informatica.exams.exam_20230207.lab.factory;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Actor;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface ActorResourceFactory {
	public Actor[] createActors(int n);

	public Resource[] createResources(int n);
}
