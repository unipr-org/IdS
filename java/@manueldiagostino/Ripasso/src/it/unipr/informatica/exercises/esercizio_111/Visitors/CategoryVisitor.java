package it.unipr.informatica.exercises.esercizio_111.Visitors;

import java.util.LinkedList;
import java.util.List;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.Node;
import it.unipr.informatica.exercises.esercizio_111.Implementations.NodeType;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class CategoryVisitor implements Visitor {
	public List<Node> leafs = new LinkedList<Node>();
	public List<Node> districts = new LinkedList<Node>();
	public List<Node> regions = new LinkedList<Node>();
	public List<Node> countries = new LinkedList<Node>();

	public void visitLeaf(Node node) {
		leafs.add(node);		
	}

	public void visitDistrict(Node node) {
		districts.add(node);
	}

	public void visitRegion(Node node) {
		regions.add(node);
	}

	public void visitCountry(Node node) {
		countries.add(node);
	}

}
