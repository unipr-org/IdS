package it.unipr.informatica.design_patterns.structural.decorator.employer;

import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Employee;
import it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts.Project;
import it.unipr.informatica.design_patterns.structural.decorator.employer.concrete.*;

public class Main {
	public static void main(String[] args) {
		Employee engineer = new Engineer("Federico", "IT Department");
		
		engineer.whoIs();
		
		System.out.println("");
		engineer = new AdministrativeManager(engineer);
		engineer.whoIs();
		
		System.out.println("");
		System.out.println("New project");
		engineer = new ProjectManager(engineer, new Project() {
			
			@Override
			public String getName() {
				return "Abstract factory project";
			}
			
			@Override
			public String getDescription() {
				return "";
			}
		});
		engineer.whoIs();

		System.out.println("");
		System.out.println("New project");
		engineer = new ProjectManager(engineer, new Project() {
			
			@Override
			public String getName() {
				return "Decorator project";
			}
			
			@Override
			public String getDescription() {
				return "";
			}
		});
		engineer.whoIs();
	}
}
