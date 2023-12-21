package it.unipr.informatica.design_patterns.structural.composite.component;

import java.util.Vector;

//oggetti composti
public class CompoundPart extends Component{

	private Vector<Component> children_;
	public CompoundPart(String name) {
		super(name);
		children_ = new Vector<>();
	}
	@Override
	public void add(Component component) {
		children_.add(component);
	}
	@Override
	public boolean remove(Component component) {
		return children_.removeElement(component);
	}
	@Override
	public Component getChild(int index) {
		return children_.elementAt(index);
	}
	@Override
	public void describe() {
		System.out.println("[CompoundPart] Component " + name_);		
	}
	@Override
	public String toString() {
		String str = "* " + name_ + " *";
		for(int i = 0; i < children_.size(); ++i)
			str = str + "\n " + children_.elementAt(i).toString();
		
		return str;
	}
}
