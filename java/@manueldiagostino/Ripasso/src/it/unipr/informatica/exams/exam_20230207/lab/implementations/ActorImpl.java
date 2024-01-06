package it.unipr.informatica.exams.exam_20230207.lab.implementations;

import java.util.Collection;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Actor;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ActorsDispatcher;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ResourcesDispatcher;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ActorImpl implements Actor {
	private final String name;
	private ActorsDispatcher actorsDispatcher;
	private ResourcesDispatcher resourcesDispatcher;
	private Thread mainCicle;

	public ActorImpl(String name, ActorsDispatcher actorsDispatcher, ResourcesDispatcher resourcesDispatcher) {
		this.name = name;
		this.actorsDispatcher = actorsDispatcher;
		this.resourcesDispatcher = resourcesDispatcher;
		mainCicle = new MainCicle();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void deliver(Message message) {
		System.out.println("[" + name + "] received " + message.value);
		if (message.value < 0)
			return;

		synchronized (name) {
			Actor actor = actorsDispatcher.getRandomActor();
			System.out.println("[" + name + "] sends " + (message.value - 1) + " to " + actor.getName());
			actor.deliver(new Message(message.value - 1));
			System.out.println("[" + name + "] delivered " + (message.value - 1));
		}
	}

	@Override
	public void start() {
		mainCicle.start();
	}

	@Override
	public void remove() {
		mainCicle.interrupt();
	}

	private class MainCicle extends Thread {
		@Override
		public void run() {
			while (true) {
				int k = (int) (1 + Math.random() * 10);
				System.out.println("[" + name + "] required " + k + " resources");
				int sum = 0;
				Resource[] res;

				try {
					res = resourcesDispatcher.getResources(k);

					for (Resource r : res) {
						r.acquire();
						sum += r.use();
						r.release();
					}

					Thread.sleep(sum * 100);
					System.out.println("[" + name + "] evaluated " + sum);

				} catch (InterruptedException e) {
					System.out.println("Actor " + name + " interrupted");
					return;
				}

				resourcesDispatcher.releaseResources(res);

				Actor actor = actorsDispatcher.getRandomActor();
				System.out.println("[" + name + "] sends " + sum + " to " + actor.getName());
				actor.deliver(new Message(sum));
				System.out.println("[" + name + "] delivered " + (sum));
			}
		}
	}

}
