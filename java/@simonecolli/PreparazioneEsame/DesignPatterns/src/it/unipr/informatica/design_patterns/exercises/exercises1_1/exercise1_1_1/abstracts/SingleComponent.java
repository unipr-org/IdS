package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts;

public abstract class SingleComponent extends Component{
	@Override
	public boolean add(Component component) {
		throw new IllegalMonitorStateException("You can't add a component to a SingleComponent");
	}
	@Override
	public boolean remove(Component component) {
		throw new IllegalMonitorStateException("You can't remove a component to a SingleComponent");
	}
	@Override
	public Component getChild(int index) {
		throw new IllegalMonitorStateException("You can't get child from a SingleComponent");		
	}
	@Override
	public NodeType getNodeType() {
		return NodeType.LEAF;
	}
}
