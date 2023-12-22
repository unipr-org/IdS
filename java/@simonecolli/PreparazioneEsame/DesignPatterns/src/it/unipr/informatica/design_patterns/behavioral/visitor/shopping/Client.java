package it.unipr.informatica.design_patterns.behavioral.visitor.shopping;

import java.util.Vector;

import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.ShoppingItem;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.abstracts.Visitor;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete.Banana;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete.FantasyBook;
import it.unipr.informatica.design_patterns.behavioral.visitor.shopping.concrete.ShoppingVisitor;

public class Client {
	public static void main(String[] args) {
		
		Vector<ShoppingItem> shoppingCart = new Vector<>();
		Visitor visitor = new ShoppingVisitor();
		
		shoppingCart.add(
			new Banana(0.5f, 2.1f)
		);
		shoppingCart.add(
			new Banana(0.5f, 2.1f)
		);
		shoppingCart.add(
			new FantasyBook(10.1f)
		);
		visitor.visit(shoppingCart);
		System.out.println("Total price: " + visitor.getTotalPrice());
	}
}
