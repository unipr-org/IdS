package it.unipr.informatica.exercises.esercizio_111.Abstractions;

import it.unipr.informatica.exercises.esercizio_111.Implementations.NodeType;
import it.unipr.informatica.exercises.esercizio_111.Iterators.Iterator;
import it.unipr.informatica.exercises.esercizio_111.Iterators.NodeBreadthFirstIterator;
import it.unipr.informatica.exercises.esercizio_111.Visitors.Visitor;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public abstract class Node {
	protected NodeType _type;
	protected String _name;

	public abstract NodeType getType();

	public abstract String getName();

	public abstract void setName(String name);

	public Node getComposite() {
		return null;
	}

	@Override
	public String toString() {
		String res = "";
		switch (_type) {
		case LEAF: {
			res += "\t\t\t[LEAF";
			break;
		}
		case DISTRICT: {
			res += "\t\t[DISTRICT";
			break;
		}
		case REGION: {
			res += "\t[REGION";
			break;
		}
		default: {
			res += "[COUNTRY";
			break;
		}
		}

		res += "]";
		res += (" " + _name);

		return res;
	}

	public abstract void accept(Visitor v);
}
