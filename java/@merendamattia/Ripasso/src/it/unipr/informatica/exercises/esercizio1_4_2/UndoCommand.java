package it.unipr.informatica.exercises.esercizio1_4_2;

public class UndoCommand implements Command {
	private Runnable task;
	
	public UndoCommand(Runnable task) {
		if(task == null)
			throw new IllegalArgumentException("task == null");
		this.task = task;
	}
	
	@Override
	public void execute() {
		new Thread(task).start();
	}
	
} // ! UndoCommand
