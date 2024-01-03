package it.unipr.informatica.esercizi.esercizio_181.lp;

import java.util.HashMap;

import it.unipr.informatica.esercizi.esercizio_181.model.Atom;
import it.unipr.informatica.esercizi.esercizio_181.model.AtomImpl;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class InterpretationContextImpl implements InterpretationContext {
	private HashMap<String, Boolean> context;
	
	public InterpretationContextImpl() {
		context = new HashMap<String, Boolean>();
	}
	
	@Override
	public void bind(String atom, boolean value) {
		if (atom == null)
			throw new IllegalArgumentException("atom == null");
		else if (!AtomImpl.isAlphNum(atom))
			throw new IllegalArgumentException("atom is not alphanumeric");
		else if (atom.equals("not"))
			throw new IllegalArgumentException("atom equals to \"not\"");
		else if (atom.equals("and"))
			throw new IllegalArgumentException("atom equals to \"and\"");
		else if (atom.equals("or"))
			throw new IllegalArgumentException("atom equals to \"or\"");
		
		context.put(atom, value);
	}
	
	public void bind(Atom atom, boolean value) {
		String name = atom.getName();
		
		bind(name,value);
	}

	@Override
	public void unbind(String atom) {
		context.remove(atom);
	}
	
	public void unbind(Atom atom) {
		context.remove(atom.getName());
	}

	@Override
	public boolean getValue(String atom) {
		return context.get(atom);
	}
	
	public boolean getValue(Atom atom) {
		return context.get(atom.getName());
	}

	@Override
	public void clear() {
		context.clear();
	}

}
