/*
 * Example11
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.util.LinkedList;
import java.util.List;

public class Example11 {
	private void go() {
		List<Integer> list = new LinkedList<>();
		
		for(int i = 0; i < 10; ++i) {
			int id = i;
			
			new Thread(() -> {
				for (int c = 0; c < 10000; ++c)
					list.add(c);
				
				System.out.println("Thread " + id + " done");
			}).start();
		}
		
		try {
			Thread.sleep(5000);

			System.out.println(list.size());
		} catch(Throwable throwable) {
			// Blank
		}
	}

	public static void main(String[] args) {
		new Example11().go();
	}
}
