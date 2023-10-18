/*
 * LinkedBlockingQueue<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public final class LinkedBlockingQueue<T> implements BlockingQueue<T> {
	private Node<T> head;

	private Node<T> tail;

	private Object mutex;

	public LinkedBlockingQueue() {
		this.head = null;

		this.tail = null;

		this.mutex = new Object();
	}

	@Override
	public T take() throws InterruptedException {
		synchronized (mutex) {
			while (head == null)
				mutex.wait();

			T result = head.value;

			head = head.next;

			if (head == null)
				tail = null;
			else
				mutex.notify();

			return result;
		}
	}

	@Override
	public void put(T object) {
		if (object == null)
			throw new NullPointerException("object == null");

		synchronized (mutex) {
			Node<T> node = new Node<>();

			node.next = null;

			node.value = object;

			if (tail == null)
				head = tail = node;
			else {
				tail.next = node;

				tail = node;
			}

			if (head.next == null)
				mutex.notify();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (mutex) {
			return head == null;
		}
	}

	@Override
	public void clear() {
		synchronized (mutex) {
			head = tail = null;
		}
	}

	@Override
	public int remainingCapacity() {
		return Integer.MAX_VALUE;
	}

	private static class Node<T> {
		private T value;

		private Node<T> next;
	}
}
