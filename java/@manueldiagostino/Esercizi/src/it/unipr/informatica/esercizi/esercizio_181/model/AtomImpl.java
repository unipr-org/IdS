package it.unipr.informatica.esercizi.esercizio_181.model;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import it.unipr.informatica.esercizi.esercizio_181.lp.Visitor;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AtomImpl implements Atom {
	private String name;
	
	public AtomImpl(String name) {
		if (name == null)
			throw new IllegalArgumentException("name == null");
		else if (!isAlphNum(name))
			throw new IllegalArgumentException("name is not alphanumeric");
		else if (name.equals("not"))
			throw new IllegalArgumentException("name equals to \"not\"");
		else if (name.equals("and"))
			throw new IllegalArgumentException("name equals to \"and\"");
		else if (name.equals("or"))
			throw new IllegalArgumentException("name equals to \"or\"");
		
		this.name = name;
	}
	
	public static boolean isAlphNum(String s) {
		
		for (int i = 0; i < s.length(); ++i) {
		    final int codePoint = s.codePointAt(i);
		    if (!Character.isAlphabetic(codePoint) && !Character.isDigit(codePoint)) {
		        return false;
		    }
		}
		
		return true;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public static void main(String[] args) {
		System.out.println(AtomImpl.isAlphNum("a"));
		System.out.println(AtomImpl.isAlphNum("a1"));
		System.out.println(AtomImpl.isAlphNum("a1_"));
	}
}
