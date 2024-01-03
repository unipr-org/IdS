package it.unipr.informatica.exercise.esercizio7;

import it.unipr.informatica.exercise.esercizio7.model.Number;
import it.unipr.informatica.exercise.esercizio7.model.Visitor;

public class NumberImpl implements Number {
	private double value;
	
	public NumberImpl(double value) { 
		this.value = value; 
	}
	
	@Override
	public double getValue() {
		return value;
	}
	
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

} // ! NumberImpl
