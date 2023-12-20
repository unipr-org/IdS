package it.unipr.informatica.design_patterns.structural.composite.component;import java.util.Vector;
import java.util.jar.Attributes.Name;

// foglia
public class SinglePart extends Component {
	
	
	public SinglePart(String name) {
		super(name);
	}
	@Override
	public void describe() {
		System.out.println("[SinglePart] Component " + name_);
	}
	@Override
	public String toString() {
		return "- " + name_;
	}
}
