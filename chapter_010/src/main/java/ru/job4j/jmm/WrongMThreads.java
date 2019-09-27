package ru.job4j.jmm;

/**
 * Class demonstrates problems with Multithreading.
 *
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 27.09.2019
 */
public class WrongMThreads {

	public static void main(String[]args) throws InterruptedException {
		var object = new WrongMThreads();
		var resource = object.new SharedResource(0);
		var thread1 = new Thread(object.new Increment(resource), "first");
		var thread2 = new Thread(object.new Increment(resource), "second");
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println(resource.count);

	}

	class Increment implements Runnable {
		SharedResource res;

		public Increment(SharedResource res) {
			this.res = res;
		}

		@Override
		public void run() {
			res.increment();
		}
	}

	class SharedResource {
		private int count;

		public SharedResource(int i) {
			this.count = i;
		}

		public void increment() {
			int j = this.count;
			if (Thread.currentThread().getName().equals("first")) {
				Thread.yield();
			}
			j++;
			this.count = j;
		}
	}
}
