package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete;

import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.BookItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.Visitor;

public class FantasyBook extends BookItem{
	
	public FantasyBook(float price) {
		super.price_ = price;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
	
}
