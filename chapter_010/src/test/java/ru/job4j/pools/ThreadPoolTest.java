package ru.job4j.pools;

import org.junit.Test;
import ru.job4j.monitor.Count;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {
	@Test
	public void whenAdd50TasksThenOK() {
		var count = new Count();
		ThreadPool pool = new ThreadPool();
		pool.init();
		for (int i = 0; i < 50; i++) {
			pool.work(new Inc(count));
		}
		pool.shutdown();

		assertThat(count.get(), is(50));
	}


	private static class Inc implements Runnable {
		Count counter;

		public Inc(Count counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			counter.increment();
		}
	}
}