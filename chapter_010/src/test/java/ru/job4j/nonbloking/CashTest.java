package ru.job4j.nonbloking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CashTest {


	@Test
	public void ifSecondThreadModifyThenThrowException() throws InterruptedException {
		AtomicReference<Exception> ex = new AtomicReference<>();
		Cash cash = new Cash();
		cash.add(new Base(1));
		cash.add(new Base(2));
		Thread thread1 = new Thread(
				() -> {
					Base base = new Base(2);
					try {
						cash.update(base);
					} catch (OptimisticException e) {
						ex.set(e);
					}
				}
		);
		Thread thread2 = new Thread(
				() -> {
					Base base = new Base(2);
					try {
						cash.update(base);
					} catch (OptimisticException e) {
						ex.set(e);
					}
				}
		);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
	}

}