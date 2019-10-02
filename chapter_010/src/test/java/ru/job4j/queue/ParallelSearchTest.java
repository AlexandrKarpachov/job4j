package ru.job4j.queue;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;



import static org.junit.Assert.*;

public class ParallelSearchTest {
	@Test
	public void whenUseOneProducerAndOneConsumerThenOK() throws InterruptedException {
		final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
		final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
		Thread producer = new Thread(
				() -> {
					IntStream.range(0, 5).forEach(
							queue::offer
					);
				}
		);
		producer.start();
		Thread consumer = new Thread(
				() -> {
					while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
						try {
							buffer.add(queue.poll());
						} catch (InterruptedException e) {
							e.printStackTrace();
							Thread.currentThread().interrupt();
						}
					}
				}
		);
		consumer.start();
		producer.join();
		consumer.interrupt();
		consumer.join();

		assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
	}

	@Test
	public void whenProducedMoreThenCapacity() throws InterruptedException {
		AtomicBoolean isEnd = new AtomicBoolean(false);
		final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
		final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
		Thread producer = new Thread(
				() -> {
					IntStream.range(0, 5).forEach(
							queue::offer
					);
					isEnd.set(true);
				}
		);
		producer.start();
		Thread consumer = new Thread(
				() -> {
					while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
						try {
							buffer.add(queue.poll());
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				}
		);
		consumer.start();
		while (!isEnd.get()) {
			Thread.sleep(500);
		}
		consumer.interrupt();
		consumer.join();

		assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
	}

}