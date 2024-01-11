package it.unipr.informatica.exams.exam_20230126;

import it.unipr.informatica.exams.exam_20230126.model.Agent;
import it.unipr.informatica.exams.exam_20230126.model.AgentVector;

public class SimpleAgentVector implements AgentVector {

	private Object[] list;
	private int size;
	private int capacity;
	private Object mutex;
	
	public SimpleAgentVector(int capacity) {
		if(capacity < 1)
			throw new IllegalArgumentException("capacity < 1");
		this.list = new Object[capacity];
		this.capacity = capacity;
		this.size = 0;
		this.mutex = new Object();
	}
	
	public SimpleAgentVector() {
		this(32);
	}
	
	@Override
	public void add(Agent item) {
		if(item == null)
			throw new IllegalArgumentException("item == null");
			
		synchronized (mutex) {
			if(size == capacity) {
				Object[] newList = new Object[capacity * 2];
				for(int i = 0; i < size; i++)
					newList[i] = list[i];
				
				capacity = capacity * 2;
				list = newList;		
			}
			list[size++] = item;
		}
	}

	@Override
	public Agent get(int index) {
		synchronized (mutex) {
			if(index < 0 || index >= size)
				throw new IllegalArgumentException("index < 0 || index >= size");
			return (Agent) list[index];
		}
	}

	@Override
	public int size() {
		synchronized (mutex) {
			return size;
		}
	}
	
	public static void main(String[] args) {
		AgentVector list = new SimpleAgentVector(5);
		
		for(int i = 0; i < 15; i++) 
			list.add(new SimpleAgent(i));
		
		for(int i = 0; i < 15; i++)
			System.out.println(list.get(i).getID());
	}

} // ! SimpleAgentVector
