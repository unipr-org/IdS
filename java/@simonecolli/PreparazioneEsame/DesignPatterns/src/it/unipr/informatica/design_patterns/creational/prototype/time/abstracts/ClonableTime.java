package it.unipr.informatica.design_patterns.creational.prototype.time.abstracts;

public abstract class ClonableTime implements Cloneable{
	public abstract void setTime(int hr, int min, int sec);
	public abstract int getHours();
	public abstract int getMinutes();
	public abstract int getSeconds();
	
	public ClonableTime clone() throws CloneNotSupportedException {
		ClonableTime myClone = (ClonableTime) super.clone();
		myClone.setTime(0, 0, 0);
		
		return myClone;
	}
}
