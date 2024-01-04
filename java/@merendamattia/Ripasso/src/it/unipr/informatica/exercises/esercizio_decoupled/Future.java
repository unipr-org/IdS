package it.unipr.informatica.exercises.esercizio_decoupled;

public interface Future {
	public Object get() throws InterruptedException, Throwable;
	public boolean isDone();
}
