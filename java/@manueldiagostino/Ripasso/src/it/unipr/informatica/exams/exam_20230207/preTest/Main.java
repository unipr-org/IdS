package it.unipr.informatica.exams.exam_20230207.preTest;

public class Main {

	public void go() {
		TaskImpl t1 = new TaskImpl();
		TaskImpl t2 = new TaskImpl();

		try {
			ConcurrentRunner.execute(t1, t2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Main().go();
	}
}
