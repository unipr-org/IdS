package it.unipr.informatica.exams.exam_20230207.lab;

public class Main {
	private void go() {
		int N = 10;
		int M = 20;
		
		Actor[] actors = ActorResourceFactory.createActors(N);
		Dispatcher disp = new Dispatcher(N, M);
		
		for(int i = 0; i < N; i++)
			actors[i].setDispatcher(disp);
		
		for(int i = 0; i < N; i++)
			actors[i].start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < N; i++)
			actors[i].stop();
		
//		actors[0].deliver(new Message(5));
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
