package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts;

import java.util.Collection;

public interface Visitor {
	public void visit(FruitItem fruit);
	public void visit(BookItem book);
	public void visit(Collection<ShoppingItem> items);
	public float getTotalPrice();
}
