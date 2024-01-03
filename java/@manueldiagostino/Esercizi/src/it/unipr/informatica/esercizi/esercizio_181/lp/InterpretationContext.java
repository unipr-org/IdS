package it.unipr.informatica.esercizi.esercizio_181.lp;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface InterpretationContext {
	public void bind(String atom, boolean value);
	public void unbind(String atom);
	public boolean getValue(String atom);
	public void clear();
}
