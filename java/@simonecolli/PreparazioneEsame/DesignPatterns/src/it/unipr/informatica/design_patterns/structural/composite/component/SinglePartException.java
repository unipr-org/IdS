package it.unipr.informatica.design_patterns.structural.composite.component;

class SinglePartException extends Exception{
	public SinglePartException() {
		super("Not supported method");
	}
}
