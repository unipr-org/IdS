package it.unipr.informatica.esercizi.esercizio_181.lp;

import it.unipr.informatica.esercizi.esercizio_181.model.AndFormula;
import it.unipr.informatica.esercizi.esercizio_181.model.Atom;
import it.unipr.informatica.esercizi.esercizio_181.model.DoubleImplicationFormula;
import it.unipr.informatica.esercizi.esercizio_181.model.Formula;
import it.unipr.informatica.esercizi.esercizio_181.model.ImplicationFormula;
import it.unipr.informatica.esercizi.esercizio_181.model.NotFormula;
import it.unipr.informatica.esercizi.esercizio_181.model.OrFormula;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class LPInterpreter implements Visitor {
	private boolean result;
	private InterpretationContext context;
	
	public LPInterpreter(InterpretationContext context) {
		if (context == null)
			throw new IllegalArgumentException("context == null");
		result = true;
		this.context = context;
	}

	@Override
	public void visit(NotFormula f) {
		f.getArg().accept(this);
		result = !result;
	}

	@Override
	public void visit(AndFormula f) {
		f.getLeft().accept(this);
		boolean partialRes = result;
		f.getRight().accept(this);
		
		result = partialRes && result;		
	}

	@Override
	public void visit(OrFormula f) {
		f.getLeft().accept(this);
		boolean partialRes = result;
		f.getRight().accept(this);
		
		result = partialRes || result;	
	}

	@Override
	public void visit(ImplicationFormula f) {
		f.getLeft().accept(this);
		boolean partialRes = result;
		f.getRight().accept(this);
		
		result = !partialRes || result;			
	}

	@Override
	public void visit(DoubleImplicationFormula f) {
		f.getLeft().accept(this);
		boolean partialRes = result;
		f.getRight().accept(this);
		
		result = (!partialRes || result) && (!result || partialRes);	
	}

	@Override
	public void visit(Atom a) {
		result = context.getValue(a.getName());
	}
	
	public boolean getResult() {
		return this.result;
	}

}
