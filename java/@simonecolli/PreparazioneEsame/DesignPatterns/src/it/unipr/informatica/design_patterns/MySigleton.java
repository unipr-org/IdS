package it.unipr.informatica.design_patterns;

public class MySigleton {
	private static MySigleton instance_ = null;
	public static MySigleton Instace() {
		if(instance_ == null)
			instance_ = new MySigleton();
		return instance_;
	}
	protected MySigleton() {}
}
