package it.unipr.informatica.exams.exam_20230126;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AgentException extends Exception {
	private static final long serialVersionUID = -1805996655302689100L;

	public AgentException(String string) {
		super(string);
	}

	public static void main(String[] args) {
		new AgentException("");
	}
}
