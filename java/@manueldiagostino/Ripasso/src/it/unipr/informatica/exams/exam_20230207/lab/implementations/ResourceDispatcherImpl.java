package it.unipr.informatica.exams.exam_20230207.lab.implementations;

import it.unipr.informatica.exams.exam_20230207.lab.abstractions.Resource;
import it.unipr.informatica.exams.exam_20230207.lab.abstractions.ResourcesDispatcher;
import it.unipr.informatica.exams.exam_20230207.lab.concurrency.ArrayBlockingQueue;
import it.unipr.informatica.exams.exam_20230207.lab.concurrency.BlockingQueue;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class ResourceDispatcherImpl implements ResourcesDispatcher {
	private BlockingQueue<Resource> resources;

	public ResourceDispatcherImpl() {
		resources = null;
	}

	public void setResources(Resource[] resources) {
		if (resources == null)
			throw new IllegalArgumentException("resources == null");

		this.resources = new ArrayBlockingQueue<Resource>(resources.length);

		for (int i = 0; i < resources.length; ++i) {
			try {
				this.resources.put(resources[i]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Resource[] getResources(int k) throws InterruptedException {
		if (k < 1 || k > resources.capacity())
			throw new IllegalArgumentException("Invalid argument for k: " + k);
		if (resources == null)
			throw new IllegalMonitorStateException("resources == null");

		synchronized (resources) { // garantisce di prendere k risorse tutte in una volta
			while (this.resources.size() < k)
				resources.wait();

			Resource[] res = new Resource[k];
			for (int i = 0; i < k; ++i)
				res[i] = this.resources.take();

			return res;
		}
	}

	@Override
	public void releaseResources(Resource[] resources) {
		for (Resource r : resources)
			try {
				this.resources.put(r); // non sarà mai bloccante
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		synchronized (resources) {
			resources.notifyAll();
		}
	}
}
