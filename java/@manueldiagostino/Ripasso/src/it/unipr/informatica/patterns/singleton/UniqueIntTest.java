package it.unipr.informatica.patterns.singleton;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class UniqueIntTest {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			try {
				Thread.sleep(2000 + (int)(Math.random()*3000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(UniqueInt.getInstance());
			UniqueInt.setValue(2 + (int)(Math.random()*3000));
			System.out.println(UniqueInt.getValue());
		};
		
		for (int i=0; i<10; ++i)
			new Thread(runnable).start();
	}
}
