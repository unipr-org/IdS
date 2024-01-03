package it.unipr.informatica.exam.model;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Add extends Expression {
	public Expression getLeft();
	public Expression getRight();
}
