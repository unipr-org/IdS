package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_2.abstracts;

public interface SystemComponent {
	public void add(SystemComponent component);
	public String getName();
	public void setName(String name);
	public void remove(SystemComponent component);
}
