package it.unipr.informatica.design_patterns.structural.decorator.employer.concrete;

import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Employee;
import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Project;
import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.ResponsibleWorker;

public class ProjectManager extends ResponsibleWorker {
	
	private Project project_;
	public ProjectManager(Employee emploee, Project project) {
		super(emploee);
		this.project_ = project;
	}
	
	@Override
	public void whoIs() {
		super.whoIs();
		System.out.println("I am a project manager for project: " + project_.getName());
	}

}
