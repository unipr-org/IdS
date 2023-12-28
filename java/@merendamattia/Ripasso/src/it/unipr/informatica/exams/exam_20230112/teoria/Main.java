package it.unipr.informatica.exams.exam_20230112.teoria;

public class Main {
	private void go() {
		A a = new AImpl();
		Callback<Integer> callback = (result) -> {
			System.out.println(result);
		};
		a.m(1000, 3000, callback);
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
