package it.unipr.informatica.threads;

public class ThreadTest {
	public static void main(String[] args) {
		Runnable pippo = () -> {
			System.out.println("Sono pippo");
		};
		
		new Thread(pippo).start();
	}
}
