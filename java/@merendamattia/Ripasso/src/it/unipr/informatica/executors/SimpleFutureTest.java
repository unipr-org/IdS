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
}
