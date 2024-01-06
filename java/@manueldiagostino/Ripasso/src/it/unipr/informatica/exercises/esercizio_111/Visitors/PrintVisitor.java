package it.unipr.informatica.exercises.esercizio_111.Visitors;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.Node;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class PrintVisitor implements Visitor {

	@Override
	public void visitLeaf(Node node) {
		System.out.println("\t\t\t[LEAF] " + node.getName());
	}

	@Override
	public void visitDistrict(Node node) {
		System.out.println("\t\t[DISTRICT] " + node.getName());
	}

	@Override
	public void visitRegion(Node node) {
		System.out.println("\t[REGION] " + node.getName());
	}

	@Override
	public void visitCountry(Node node) {
		System.out.println("[COUNTRY] " + node.getName());
	}

}
