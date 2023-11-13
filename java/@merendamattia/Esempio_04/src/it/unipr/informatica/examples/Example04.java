package it.unipr.informatica.examples;

import java.util.function.UnaryOperator;
import it.unipr.informatica.concurrent.atomic.AtomicReference;

public class Example04 {
	private void go() {
		// Gestisce l'oggetto in mutua esclusione
		AtomicReference<Integer> counter = new AtomicReference<>(1);

		int i = counter.get();

		Incrementer incrementer = new Incrementer(); // Creo un nuovo operatore unario

		while (i <= 10) {
			System.out.println("Using inner class: " + i);

			i = counter.updateAndGet(incrementer);
		}

		// Classe interna anonima
		UnaryOperator<Integer> operator = new UnaryOperator<Integer>() {
			@Override
			public Integer apply(Integer value) {
				return value + 1;
			}
		};

		while (i <= 20) {
			System.out.println("Using anonymous inner class: " + i);

			i = counter.updateAndGet(operator);
		}

		while (i <= 30) {
			System.out.println("Using lambda function: " + i);

			i = counter.updateAndGet((x) -> {
				return x + 1;
			});
		}

		while (i <= 40) {
			System.out.println("Using lambda expression: " + i);

			i = counter.updateAndGet((x) -> x + 1);
		}

		while (i <= 50) {
			System.out.println("Using method reference: " + i);

			i = counter.updateAndGet(this::increment);
		}
	}

	private int increment(int x) {
		return x + 1;
	}

	public static void main(String[] args) {
		new Example04().go();
	}

	// Classe interna
	private static class Incrementer implements UnaryOperator<Integer> {
		@Override
		public Integer apply(Integer value) {
			return value + 1;
		}
	}
}