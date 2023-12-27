package it.unipr.informatica.exercises.esercizio1_4_2;

public class Main {
	private void go() {
		UndoQueue<String> queue = LoggingAspect.attach(new SimpleUndoQueue<String>());
		
		queue.enqueue("Ciao");
		queue.enqueue("a");
		queue.enqueue("tutti");
		queue.enqueue("come");
		queue.enqueue("vi");
		queue.enqueue("chiamate?");
		
		System.out.println(queue);
		
		queue.dequeue();
		
		System.out.println(queue);
		
		queue.undo();
		
		System.out.println(queue);
		
		queue.undo();
		
		System.out.println(queue);
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
