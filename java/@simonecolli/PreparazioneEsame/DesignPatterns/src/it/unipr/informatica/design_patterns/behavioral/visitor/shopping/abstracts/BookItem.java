package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts;

public abstract class BookItem implements ShoppingItem{
	protected float price_;
	
	public float getCost() {
		return price_;
	}
}
