package it.unipr.informatica.exams.exam_20230207.teoria;

public class Main {
	private void go() {
		Task t1 = new TaskImpl();
		Task t2 = new TaskImpl();
		
		try {
			CuncurrentRunner.execute(t1, t2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
