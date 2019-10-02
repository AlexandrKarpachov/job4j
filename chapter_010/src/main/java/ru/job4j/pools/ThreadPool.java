package ru.job4j.pools;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
	private final List<Thread> threads = new LinkedList<>();
	private final int size = Runtime.getRuntime().availableProcessors();
	private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

	public void init() {
		for (int i = 0; i < this.size; i++) {
			this.threads.add(new Worker());
		}
		this.threads.forEach(Thread::start);
	}

	public void work(Runnable job) {
		tasks.offer(job);

	}

	public void shutdown() {
		for (Thread thread : this.threads) {
			thread.interrupt();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private final class Worker extends Thread {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					tasks.poll().run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
