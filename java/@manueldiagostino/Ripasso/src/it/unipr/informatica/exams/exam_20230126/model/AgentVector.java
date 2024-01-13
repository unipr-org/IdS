package it.unipr.informatica.exams.exam_20230126.model;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface AgentVector {
	public void add(Agent agent);
	public Agent get(int i);
	public Agent[] getArray();
	public int getSize();
}
