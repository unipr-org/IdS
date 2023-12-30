package it.unipr.informatica.exercise.esercizio1;

public class Exercise01 {
	public static void main(String[] args) {
		Activity activity = new ActivityImpl();
		
		Thread thread1 = activity.perform("one");		
		Thread thread2 = activity.perform(2);
		Thread thread3 = activity.perform(Float.valueOf(1.5f));
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
