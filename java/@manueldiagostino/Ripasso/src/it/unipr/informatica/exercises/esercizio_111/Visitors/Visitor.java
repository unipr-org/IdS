package it.unipr.informatica.exercises.esercizio_111.Visitors;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.Node;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public interface Visitor {
	public void visitLeaf(Node node);

	public void visitDistrict(Node node);

	public void visitRegion(Node node);

	public void visitCountry(Node node);
}
