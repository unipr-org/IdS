package it.unipr.informatica.design_patterns.structural.decorator.employer.abstracts;

// decorator
public abstract class ResponsibleWorker implements Employee{
	protected Employee responsible_;
	
	protected ResponsibleWorker(Employee emploee) {
		responsible_= emploee;
	}
	
	@Override
	public String getName() {
		return responsible_.getName();
	}
	@Override
	public String getOffice() {
		return responsible_.getOffice();
	}
	@Override
	public void whoIs() {
		responsible_.whoIs();
	}
}
