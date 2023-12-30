package it.unipr.informatica.examples.esempio12;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import it.unipr.informatica.aspects.LoggingAspect;

public class Esempio12 {
	private void go() {
		Comparator<String> comparator = new CaseInsensitiveComparator();
		
		Set<String> set = LoggingAspect.attach(new TreeSet<>(comparator));
		
		set.add("Verdi");
		set.add("Bianchi");
		set.add("Neri");
		set.add("Rossi");
		
		set.contains("Neri");
		set.contains("Viola");
	}

	public static void main(String[] args) {
		new Esempio12().go();
	}
	
	private static class CaseInsensitiveComparator implements Comparator<String> {
		@Override
		public int compare(String left, String right) {
			if (left == null || right == null)
				throw new IllegalArgumentException("o1 == null || o2 == null");
						
			return left.compareToIgnoreCase(right);
		}
	}
}