package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 30.09.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

	@GuardedBy("this")
	private Queue<T> queue = new LinkedList<>();
	private final int capacity;

	public SimpleBlockingQueue(int capacity) {
		this.capacity = capacity;
	}

	public  void offer(T value) {
		synchronized (this) {
			while (queue.size() == capacity) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			queue.add(value);
			this.notify();
		}

	}

	public T poll() throws InterruptedException {
		synchronized (this) {
			while (this.queue.size() == 0) {
				this.wait();
			}
			T result = queue.poll();
			this.notify();
			return result;
		}
	}

	public boolean isEmpty() {
		synchronized (this) {
			return this.queue.isEmpty();
		}
	}
}