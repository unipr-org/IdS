package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts;

import java.util.Vector;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete.District;

public abstract class Compound extends Component {
	
	protected NodeType node_;
	protected Vector<Component> child_;
	
	@Override
	public NodeType getNodeType() {
		return node_;
	}
	@Override
	public boolean add(Component component) {
		if(component == null)
			throw new IllegalArgumentException("component == null");

		if(this.node_ == NodeType.COUNTRY && component.getNodeType() != NodeType.REGION)
			throw new RuntimeException("this.node_ == NodeType.COUNTRY && component.getNodeType() != NodeType.REGION");
		if(this.node_ == NodeType.REGION && component.getNodeType() != NodeType.DISTRICT)
			throw new RuntimeException("this.node_ == NodeType.REGION && component.getNodeType() != NodeType.DISTRICT");
		if(this.node_ == NodeType.DISTRICT && component.getNodeType() != NodeType.LEAF)
			throw new RuntimeException("this.node_ == NodeType.DISTRICT && component.getNodeType() != NodeType.LEAF");
		return child_.add(component);
	}
	@Override
	public boolean remove(Component component) {
		if(component == null)
			throw new IllegalArgumentException("component == null");
		return child_.remove(component);
	}
	
}
