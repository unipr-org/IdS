package it.unipr.informatica.concurrent.pool;

public interface Executor {
	public void execute(Runnable command);
}
