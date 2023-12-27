package it.unipr.informatica.exercises.esercizio1_4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimpleUndoQueue<T> implements UndoQueue<T>{

	private List<T> queue;
	private Stack<Runnable> undoCmdStack;
	
	public SimpleUndoQueue(){
		queue = new ArrayList<T>();
		undoCmdStack = new Stack<Runnable>();
	}
	
	@Override
	public void enqueue(T elem) {
		if(elem == null)
			throw new IllegalArgumentException("elem == null");
		
		queue.add(elem);
		Runnable cmd = new Runnable() {
			@Override
			public void run() {
				this.removeBack();
			}

			private void removeBack() {
				List<T> newQueue = new ArrayList<T>();
				
				for(int i = 0; i < queue.size() - 1; i++)
					newQueue.add(queue.get(i));
				
				queue = newQueue;
			}
		};
		undoCmdStack.push(cmd);
	}

	@Override
	public T dequeue() {
		if(queue.size() == 0)
			throw new IllegalArgumentException("queue.size() == 0");
		
		List<T> newQueue = new ArrayList<T>();
		T result = queue.get(0);
		
		for(int i = 1; i < queue.size(); i++)
			newQueue.add(queue.get(i));
		
		queue = newQueue;
		
		Runnable cmd = new Runnable() {
			@Override
			public void run() {
				this.addFront(result);
			}

			private void addFront(T elem) {
				List<T> newQueue = new ArrayList<T>();
				newQueue.add(elem);
				
				for(int i = 0; i < queue.size(); i++)
					newQueue.add(queue.get(i));
				
				queue = newQueue;
			}
		};
		undoCmdStack.push(cmd);
		
		return result;
	}

	@Override
	public void clear() {
		queue.clear();
	}

	@Override
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	@Override
	public void undo() {
		new UndoCommand(undoCmdStack.pop()).execute();
	}
	
	@Override
	public String toString() {
		String result = "{";
		
		for(int i = 0; i < queue.size() - 1; i++)
			result += queue.get(i) + ", ";
		
		result += queue.get(queue.size() - 1) + "}";
		
		return result;
	}

} // ! SimpleUndoQueue<T>
