package exam_2023_01_12.theory.exercise2.it.unipr.informatica.exam;

public class Main {
	public static void main(String[] args) {
		A a = new AImpl();
		Callback<Integer> callback = (Integer integ) -> {
			System.out.println("i: " + integ);
		};
		for(int i = 0; i < 100; ++i)
			a.m(10, 20, callback);
	}
}
