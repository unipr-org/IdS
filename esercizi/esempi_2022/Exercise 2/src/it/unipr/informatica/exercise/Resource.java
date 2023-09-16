package it.unipr.informatica.exercise;

public interface Resource {
	public int getID();
	
	public int use();
	
	public void release();
}
