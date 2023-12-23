package it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete;

import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.FruitItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.Visitor;

public class Banana extends FruitItem{
	
	public Banana(float weightInKg, float priceForKg) {
		super.weightInKg_ = weightInKg;
		super.priceForKg_ = priceForKg;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
