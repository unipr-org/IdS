package it.unipr.informatica.exams.exam_20230112.lab;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensorImpl implements TemperatureSensor {
	private int ID;
	private double temperature;
	private boolean isDone;
	private List<TemperatureObserver> observer;
	private Object mutex;
	private ExecutorService pool;
	private Thread thermometer;
	
	public TemperatureSensorImpl(int ID) {
		this.ID = ID;
		this.temperature = -1000000;
		this.isDone = false;
		this.observer = new ArrayList<TemperatureObserver>();
		this.mutex = new Object();
		this.pool = Executors.getInstance();
		this.thermometer = new Thermometer();
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public double getTemperature() {
		synchronized (mutex) {
			if(temperature == -1000000)
				throw new NullPointerException("temperature == null");
			return temperature;
		}
	}

	@Override
	public void start() {
		synchronized (mutex) {
			thermometer.start();	
		}
	}

	@Override
	public void stop() {
		synchronized (mutex) {
			if(isDone) 
				throw new IllegalMonitorStateException("Sensor alredy stopped");
			isDone = true;
		}
	}

	@Override
	public void attach(TemperatureObserver o) {
		if(o == null)
			throw new IllegalArgumentException("o == null");
		
		synchronized (mutex) {
			observer.add(o);
		}
	}

	@Override
	public void detach(TemperatureObserver o) {
		if(o == null)
			throw new IllegalArgumentException("o == null");
		
		synchronized (mutex) {
			observer.remove(o);
		}
	}
	
	@Override
	public void notifyAllObserver() {
		for (TemperatureObserver ob: observer) {
			Runnable up = new Runnable() {
				@Override
				public void run() {
					ob.update(TemperatureSensorImpl.this);
				}
				
			};
			pool.execute(up);
		}
	}
	
	@Override
	public String toString() {
		synchronized (mutex) {
			return "[Sensor" + ID + ", temperature = " + temperature + "]";	
		}
	}
	
	private class Thermometer extends Thread {
		@Override
		public void run() {
			while(true) {
				synchronized (mutex) {
					if(isDone)
						return;
					temperature = Math.random() * 10;
				}
				
				notifyAllObserver();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.err.println(Thread.currentThread().getName() + " interrupted while waiting for a new temperature");
					return;
				}
			}
		}
	} // ! Thermometer

} // ! TemperatureSensorImpl
