package it.unipr.informatica.exercises.esercizio1_1_1;

public class Leaf extends Component{

	public Leaf(String name) {
		this.name = name;
		this.type = ComponentType.LEAF;
	}
	
	@Override
	public void add(Component item) {
		throw new IllegalAccessError("add on a leaf");
	}

	@Override
	public boolean remove(Component item) {
		throw new IllegalAccessError("remove on a leaf");
	}

	@Override
	public void operation() {
		System.out.println(this + " doing something...");
	}
	
	@Override
	public String toString() {
		return type + " - " + name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

} // ! Leaf
