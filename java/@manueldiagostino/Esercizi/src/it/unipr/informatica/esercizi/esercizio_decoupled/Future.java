package it.unipr.informatica.esercizi.esercizio_decoupled;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Future {
	public void setValue(Object value);
	public void setException(Exception e);
	public Object getValue() throws InterruptedException;
	public boolean isDone();
}
