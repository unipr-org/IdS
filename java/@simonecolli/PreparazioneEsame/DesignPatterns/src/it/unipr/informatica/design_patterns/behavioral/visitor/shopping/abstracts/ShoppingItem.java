package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts;

public interface ShoppingItem {
	public void accept(Visitor visitor);
}
