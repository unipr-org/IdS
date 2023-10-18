package it.unipr.informatica.exercise;

public class Exercise2 {
	public static void main(String[] args) {
		for (int i = 0; i < Worker.W; ++i)
			new Worker(i).start();
	}
}
