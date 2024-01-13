package it.unipr.informatica.exams.exam_20230126.model;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface AgentHandler {
	public Agent getRandomAgent();
	public Agent[] getAll();
	public Agent get(int i);
	public void add(Agent agent);
	public void delete();
}
