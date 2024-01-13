package it.unipr.informatica.blockingQueue;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class ArrayBlockingQueueTest2 {

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#ArrayBlockingQueue(int)}.
	 */
	@Test
	public void testArrayBlockingQueue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#put(java.lang.Object)}.
	 * @throws InterruptedException 
	 */
	@Test 
	public void testPut() throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		queue.put(1);
		Integer i = queue.take();
		assert(i == 1);
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#take()}.
	 */
	@Test
	public void testTake() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#clear()}.
	 */
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#isFull()}.
	 */
	@Test
	public void testIsFull() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#remainingCapacity()}.
	 */
	@Test
	public void testRemainingCapacity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#print()}.
	 */
	@Test
	public void testPrint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link it.unipr.informatica.blockingQueue.ArrayBlockingQueue#getSize()}.
	 */
	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

}
