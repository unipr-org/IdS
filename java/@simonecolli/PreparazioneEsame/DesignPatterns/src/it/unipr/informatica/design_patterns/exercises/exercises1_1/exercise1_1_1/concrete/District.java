package it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.concrete;

import java.util.Vector;

import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.Component;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.Compound;
import it.unipr.informatica.design_patterns.exercises.exercises1_1.exercise1_1_1.abstracts.NodeType;

public class District extends Compound{
	private String name_;
	public District(String name) {
		super();
		super.node_ = NodeType.DISTRICT;
		super.child_ = new Vector<>();
		this.name_ = name;
	}
	@Override
	public Component getChild(int index) {
		if(index < 0 || index >= child_.size())
			throw new IllegalArgumentException("index < 0 || index >= child_.size()");
		return child_.elementAt(index);
	}

	@Override
	public String getDescription() {
		String ret = "\n[" + node_ + "] " + name_;
		for(Component c : child_)
			ret += c.getDescription();

		return ret;
	}

}
