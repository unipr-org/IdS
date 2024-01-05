package it.unipr.informatica.exams.exam_20230207.preTest;

public class TaskImpl implements Task {

	@Override
	public Object run() {
		int time = 1500 + (int) (Math.random() * 3000);
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println(time);
		return time;
	}

}
