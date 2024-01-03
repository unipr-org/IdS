package it.unipr.informatica.exam.Implementations;

import java.util.LinkedList;
import java.util.List;

import it.unipr.informatica.exam.Abstractions.TemperatureObserver;
import it.unipr.informatica.exam.Abstractions.TemperatureSensor;
import it.unipr.informatica.exam.pool.ThreadPool;
import it.unipr.informatica.exam.pool.ThreadPoolsHandler;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class TemperatureSensorImpl implements TemperatureSensor{
	private final int id;
	private double temperature;
	private final Object mutex;
	
	private Thread thermometer;
	private boolean stop;
	
	private List<TemperatureObserver> observers;
	private ThreadPool threadPool;
	
	public TemperatureSensorImpl(int id) {
		this.id = id;
		this.temperature = 0;
		this.mutex = new Object();
		
		this.stop = false;
		this.thermometer = new Thermometer();
		
		this.observers = new LinkedList<TemperatureObserver>();
		this.threadPool = ThreadPoolsHandler.newThreadPool();
	}
	
	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public double getTemperature() {
		synchronized (mutex) {
			return temperature;
		}
	}
	
	
	private void updateAll() {
		synchronized (observers) {			
			for (TemperatureObserver o : observers) {
				Runnable runnable = new Runnable() {
					
					@Override
					public void run() {
						o.update(TemperatureSensorImpl.this);
					}
				};
				
				threadPool.execute(runnable);
			}
		}
	}

	@Override
	public void start() {
		synchronized (mutex) {
			this.thermometer.start();
		}
	}

	@Override
	public void stop() {
		synchronized (mutex) {
			this.stop = true;
			this.thermometer.interrupt();
			
			this.observers.clear();
			this.threadPool.shutdown();
		}
	}

	@Override
	public void attach(TemperatureObserver o) {
		synchronized (observers) {
			observers.add(o);
		}
	}

	@Override
	public void detach(TemperatureObserver o) {
		synchronized (observers) {
			observers.remove(o);
		}
	}
	
	private class Thermometer extends Thread {
		@Override
		public void run() {
			while (true) {
				if (stop) {					
					//System.out.println("Thermometer stopped");
					return;
				}
				
				double res = Math.random();
				boolean bernoullian = res>=0.5 ? true : false;
				
				synchronized (mutex) {					
					if (bernoullian)
						temperature = 15.0 + res*3.4;
					else
						temperature = 15.0 - res*3.4;
				}
				
				updateAll();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					//System.out.println("Thermometer interrupted");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		TemperatureSensorImpl s0 = new TemperatureSensorImpl(0);
		TemperatureObserver o0 = new PrintObserver();
		
		s0.attach(o0);
		s0.start();
		
		try {
			System.out.println("Main sleeping");
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s0.stop();
		System.out.println("Main returned");
	}

}
