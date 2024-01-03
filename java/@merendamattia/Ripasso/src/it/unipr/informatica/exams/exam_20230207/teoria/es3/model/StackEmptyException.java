package it.unipr.informatica.exams.exam_20230207.teoria.es3.model;

public class StackEmptyException extends Exception {

	public StackEmptyException() {
		System.err.println("Stack empty cannot execute pop");
	}
} // ! StackEmptyException
