package it.unipr.informatica.design_patterns.structural.decorator.employer.concrete;

import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Employee;

public class Engineer implements Employee{
	
	private String name_;
	private String office_;
	public Engineer(String name, String office) {
		this.name_ = name;
		this.office_ = office;
	}
	
	@Override
	public String getName() {
		return name_;
	}

	@Override
	public String getOffice() {
		return office_;
	}

	@Override
	public void whoIs() {
		System.out.println("Hi I'm " + name_ + ", and my office is " + office_);
	}
}
