package it.unipr.informatica.exams.exam_20230207.teoria.es3;

import it.unipr.informatica.exams.exam_20230207.teoria.es3.model.Command;

public class UndoCommand implements Command {
	private Runnable task;
	
	public UndoCommand(Runnable task) {
		if(task == null)
			throw new IllegalArgumentException("task == null");
		this.task = task;
	}
	
	@Override
	public void execute() {
		task.run();
	}

} // ! UndoCommand
