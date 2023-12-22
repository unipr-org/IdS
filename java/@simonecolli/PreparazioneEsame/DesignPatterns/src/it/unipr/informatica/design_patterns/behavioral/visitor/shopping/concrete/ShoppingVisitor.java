package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete;

import java.util.Collection;

import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.BookItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.FruitItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.ShoppingItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.Visitor;

public class ShoppingVisitor implements Visitor{
	
	private float totalPrice_;
	public ShoppingVisitor() {
		totalPrice_ = 0;
	}
	
	@Override
	public void visit(Collection<ShoppingItem> items) {
		for (ShoppingItem item : items)
				item.accept(this);
		
	}

	@Override
	public void visit(FruitItem fruit) {
		totalPrice_ += fruit.getCost();
	}

	@Override
	public void visit(BookItem book) {
		totalPrice_ += book.getCost();
	}

	@Override
	public float getTotalPrice() {
		return totalPrice_;
	}

	
}
