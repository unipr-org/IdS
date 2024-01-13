package it.unipr.informatica.exams.exam_20240109.teoria.es3;

public class Main {
	public static void main(String[] args) {
		Tuple<String> t = new Tuple<String>(3);
		
		t.add("Ciao");
		t.add("a");
		t.add("tutti");
		
		Iterator<String> it = t.iterator();
		
		while(it.hasNext())
			System.out.println(it.next());
		
	}
}
