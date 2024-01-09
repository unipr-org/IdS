package exam_2024_01_09.es3;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Tuple<String> t = new Tuple<>(3);
		
		t.add("Ciao");
		t.add("a");
		t.add("tutti");
		
		Iterator<String> it = t.iterator();
		
		while(it.hasNext())
			System.out.println(it.next());
	}
}
