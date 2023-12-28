package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.NodeType;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.SingleComponent;

public class Leaf extends SingleComponent{
	private String name_;
	public Leaf(String name) {
		this.name_ = name;
	}
	@Override
	public String getDescription() {
		return "\n[" + NodeType.LEAF + "] " + name_;
	}
	
}
