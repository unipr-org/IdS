package it.unipr.informatica.exercises.esercizio_decoupled;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private void go() {
		List<String> l = DecoupledAspect.attach(new ArrayList<String>());
		l.add("Ciao");
		l.add("a");
		l.add("tutti");
		System.out.println(l);
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
}
