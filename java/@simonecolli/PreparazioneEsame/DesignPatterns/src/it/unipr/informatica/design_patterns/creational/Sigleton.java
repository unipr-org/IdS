package it.unipr.informatica.design_patterns.creational;

public class Sigleton {
	private volatile static Sigleton instance_ = null;
	private static Object mutex_ = new Object();
	public static Sigleton Instace() {
		if(instance_ == null)
			synchronized (mutex_) {
				if(instance_ == null)
					instance_ = new Sigleton();				
			}
		
		return instance_;
	}
	private Sigleton() {}
}
