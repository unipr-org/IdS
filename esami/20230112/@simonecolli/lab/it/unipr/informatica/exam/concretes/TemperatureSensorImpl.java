package exam_2023_01_12.lab.it.unipr.informatica.exam.concretes;

import java.util.Vector;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensorAbstractFactory;
import exam_2023_01_12.lab.it.unipr.informatica.exam.pool.ThreadPool;
import exam_2023_01_12.lab.it.unipr.informatica.exam.Enviroment;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureObserver;
import exam_2023_01_12.lab.it.unipr.informatica.exam.abstracts.TemperatureSensor;

public class TemperatureSensorImpl implements TemperatureSensor{
	
	private Object sensorMutex_;
	private int id_;
	private boolean usable_;
	private volatile boolean isRunning_;
	private ThreadPool myPool_;
	private TemperatureHandler temperatureHandler_;
	
	private Vector<TemperatureObserver> observers_;
	
	public TemperatureSensorImpl(int id) {
		if(id <= 0)
			throw new IllegalArgumentException("id <= 0");
		this.id_ = id;
		this.myPool_ = null;
		this.usable_ = true;
		this.isRunning_ = false;
		this.temperatureHandler_ = null;
		this.observers_ = new Vector<>();
		this.sensorMutex_ = new Object();
	}
	
	@Override
	public int getID() {
		return id_;
	}

	@Override
	public double getTemperature() {
		if(temperatureHandler_ == null)
			throw new IllegalMonitorStateException("temperatureHandler_ == null");
		
		return temperatureHandler_.getTemperature();
	}

	@Override
	public void start() {
		
		synchronized (sensorMutex_) {
			if(!usable_)
				throw new IllegalMonitorStateException("!usable_");
			if(!isRunning_) {
				isRunning_ = true;
				myPool_ = new ThreadPool();
				temperatureHandler_ = new TemperatureHandler();
				new Thread(temperatureHandler_).start();			
			}
		}
	}

	@Override
	public void stop() {
		synchronized (sensorMutex_) {
			if(!usable_)
				throw new IllegalMonitorStateException("!usable_");
			if(!isRunning_)
				throw new IllegalMonitorStateException("!isRunning_");
			
			myPool_.shutdown();
			
			isRunning_ = false;
			usable_ = false;
			System.out.println("sensor stoped");
		}
	}

	@Override
	public void attach(TemperatureObserver o) {
		synchronized (observers_) {
			observers_.add(o);			
		}
	}

	@Override
	public void detach(TemperatureObserver o) {
		synchronized (observers_) {
			observers_.remove(o);
		}		
	}
	private void updateAll() {
		synchronized (observers_) {
			for (TemperatureObserver o : observers_) {
				o.update(this);
			}
		}
	}
	
	private class TemperatureHandler implements Runnable{
		
		private double lastTemperature_;
		private Object mutex_;
		
		private TemperatureHandler() {
			this.lastTemperature_ = 0;
			this.mutex_ = new Object();
		}
		private double getTemperature() {
			synchronized (mutex_) {
				return lastTemperature_;				
			}
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				synchronized (sensorMutex_) {
					if(!isRunning_) {
						System.out.println("temperature handler stop");
						return;
					}
				}

				double temperature = Enviroment.getInstance().getCurrentTemperature();
				synchronized (mutex_) {
					this.lastTemperature_ = temperature;
				}
				
				Runnable update  = () -> {
					updateAll();
				};
				myPool_.execute(update);
				
				
			}
		}
	}

}
