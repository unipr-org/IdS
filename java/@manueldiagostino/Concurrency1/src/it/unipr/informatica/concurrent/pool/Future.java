package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Future<ValueType> {
	public ValueType get() throws InterruptedException, Throwable;
	public void setValue(ValueType value);
	public void setException(Throwable exception);
	public boolean isDone();
}
