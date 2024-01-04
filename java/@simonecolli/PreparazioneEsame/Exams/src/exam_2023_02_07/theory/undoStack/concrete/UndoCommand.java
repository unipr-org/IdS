package exam_2023_02_07.theory.undoStack.concrete;

import exam_2023_02_07.theory.undoStack.abstracts.Command;


public class UndoCommand implements Command{
	
	private Runnable command_;
	public UndoCommand(Runnable command) {
		command_ = command;
	}
	@Override
	public void execute() {
		command_.run();
	}

}
