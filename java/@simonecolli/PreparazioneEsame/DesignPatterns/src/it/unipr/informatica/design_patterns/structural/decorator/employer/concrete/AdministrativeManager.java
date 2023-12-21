package it.unipr.informatica.design_patterns.structural.decorator.employer.concrete;

import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Employee;
import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.ResponsibleWorker;

public class AdministrativeManager extends ResponsibleWorker {

	public AdministrativeManager(Employee emploee) {
		super(emploee);
	}
	@Override
	public void whoIs() {
		sayIamTheManager();
		super.whoIs();
	}
	private void sayIamTheManager() {
		System.out.println("I am the administrative manager");
	}

}
