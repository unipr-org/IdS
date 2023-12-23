package it.unipr.informatica.esercizi.esercizio_111.Iterators;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Iterator {
	public Object first();
	public Object next();
	public Object currentItem();
	public boolean isDone();
}
