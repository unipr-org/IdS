package it.unipr.informatica.executors;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleFutureTest {
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetValue() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setValue(1);
		f.setValue(2);
	}
	
	@Test
	public void testIsDone() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setValue(1);
		
		assertTrue(f.isDone());
	}
	
	@Test
	public void testIsNotDone() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		
		assertFalse(f.isDone());
	}
	
	@Test(expected = Throwable.class)
	public void testSetException() throws Throwable {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setException(new Throwable("testSetException()"));
		f.get();
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetNullException() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setException(null);
	}
	
	@Test
	public void testGetValue() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setValue(1);
		
		Integer result = 0;
		try {
			result = (Integer) f.get();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		assertTrue(1 == result);
	}
	
	
	@Test(expected = InterruptedException.class)
	public void testInterruptedException() throws InterruptedException, Throwable {
		Thread currentThread = Thread.currentThread();
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		
		Runnable runnable = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentThread.interrupt();
		};
		
		new Thread(runnable).start();
		f.get();
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void setIllegalMonitorStateException() {
		SimpleFuture<Integer> f = new SimpleFuture<Integer>();
		f.setValue(1);
		f.setException(new Throwable());
	}
	
	// ------------------------------ Test di Manuel
	
	@Test
	public void testSetValue2() {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		Integer result = null;
		try {
			result = future.get();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals((Integer)0, result);
	}
	
	@Test(expected = Throwable.class)
	public void testSetException2() throws Throwable {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setException(new Throwable("Throwable exception"));

		future.get();
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetNullException2() throws Throwable, IllegalMonitorStateException  {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setException(null);

		future.get();
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetIllegalMonitorStateException() throws Throwable, IllegalMonitorStateException {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		future.setException(new Throwable("Throwable exception"));
	}
	
	@Test
	public void testGetValue2() throws Throwable {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		assertEquals((Integer)0, future.get());
	}

	@Test(expected = Throwable.class)
	public void testGetException() throws Throwable {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setException(new Throwable("Throwable exception"));
		future.get();
	}
	
	@Test(expected = InterruptedException.class)
	public void testGetInterruptedException() throws InterruptedException, Throwable {
		Thread parentThread = Thread.currentThread();
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		
		Runnable runnable = () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			parentThread.interrupt();
		};
		
		
		new Thread(runnable).start();
		future.get();
	}
}
