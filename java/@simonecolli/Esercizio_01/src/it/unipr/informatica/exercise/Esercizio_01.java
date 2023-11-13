package it.unipr.informatica.exercise;

public class Esercizio_01 {
	public static void main(String[] args) {
		
		Activity activity = new ActivityImpl();
		
		Thread thread1 = activity.perform("one");
		Thread thread2 = activity.perform(2); // boxing
		Thread thread3 = activity.perform(Float.valueOf(1.5f)); // boxing esplicito
		
		
		thread1.start();
		thread2.start();
		thread3.start();
	
	}
}
