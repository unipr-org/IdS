package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts;

public abstract class FruitItem implements ShoppingItem {
	protected float priceForKg_;
	protected float weightInKg_;
	
	public float getCost() {
		return priceForKg_*weightInKg_;
	}
}
