package it.unipr.informatica.pools.executors;

import org.junit.Test;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;

public class SimpleFutureTest {
	@Test
	public void testSetValue() {

		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		Integer res = null;
		try {
			res = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertEquals(0, res.intValue());
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetValueIllegalState() {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		future.setValue(1);
		
	}
	
	
	@Test(expected = RuntimeException.class)
	public void testSetThrowable() throws Throwable{
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setThrowable(new RuntimeException());
		future.get();
	}
	
	@Test(expected = IllegalMonitorStateException.class)
	public void testSetThrowableIllegalMonitor() throws Throwable{
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setThrowable(new RuntimeException());
		future.setThrowable(new RuntimeException());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNullThrowable() {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setThrowable(null);
	}
	
	@Test
	public void testIsDone() {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setValue(0);
		assertEquals(true, future.isDone());
	}
	@Test
	public void testIsNotDone() {
		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		assertEquals(false, future.isDone());
	}
	
	@Test(expected = InterruptedException.class)
	public void testGetInterrupted() throws Throwable{

		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		Thread parent = Thread.currentThread();
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				parent.interrupt();
			}
		};
		new Thread(runnable).start();
		future.get();
	}
	
	
	@Test(expected = Throwable.class)
	public void testGetException() throws Throwable{

		SimpleFuture<Integer> future = new SimpleFuture<Integer>();
		future.setThrowable(new RuntimeException());
		future.get();
	}
	

}
