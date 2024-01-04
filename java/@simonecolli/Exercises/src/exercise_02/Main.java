package exercise_02;

import exercise_02.abstracts.Launcher;
import exercise_02.abstracts.Task;
import exercise_02.concretes.LauncherImpl;

public class Main {
	public static void main(String[] args) {
		Launcher l = new LauncherImpl();
		Task[] ts = new Task[20];
		for(int i = 0; i < ts.length; ++i) {
			int tmp = i;
			ts[i] = () -> {
				System.out.println("task " + tmp + " start performing");
				try {
					Thread.sleep((int) (Math.random() * 1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				System.out.println("task " + tmp + " end performing");
			};
		}
		l.start(ts);
	}
}
