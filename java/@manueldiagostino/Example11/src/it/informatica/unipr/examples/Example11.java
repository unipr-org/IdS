package it.informatica.unipr.examples;
import java.util.LinkedList;
import java.util.List;
import it.informatica.unipr.aspects.SharedAspect;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Example11 {
	public void go() {
//		List<Integer> list = new LinkedList<>();
		List<Integer> list = SharedAspect.attach(new LinkedList<Integer>());
		for (int i=0; i<10; ++i) {
			Integer id = i;
			
			new Thread(() -> {
				for (int j=0; j<10000; ++j)
					list.add(j);
				
				System.out.println("Thread " + "[" + id + "]" + " terminated");
			}).start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("List size: " + list.size());
	}
	
	public static void main(String[] args) {
		new Example11().go();
	}

}
