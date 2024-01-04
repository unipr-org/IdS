package exercise_04.abstracts;

public interface MonitorSet {
	public boolean add(Monitor m);
	public boolean await() throws InterruptedException;
}
