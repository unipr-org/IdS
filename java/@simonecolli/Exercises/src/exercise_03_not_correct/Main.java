package exercise_03_not_correct;

import exercise_03_not_correct.abstracts.Executor;
import exercise_03_not_correct.abstracts.Task;
import exercise_03_not_correct.concrete.ExecutorImpl;
import exercise_03_not_correct.concrete.TaskImpl;

public class Main {
	public static void main(String[] args) {
		Executor l = new ExecutorImpl();
		Task[] ts = new Task[20];
		for(int i = 0; i < ts.length; ++i)
			ts[i] = new TaskImpl(i);
		
		l.launch(ts);
	}
}
