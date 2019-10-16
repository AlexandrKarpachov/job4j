package ru.job4j.exam;

import java.util.concurrent.CountDownLatch;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 10.10.2019
 */
public class DeadLock {
	private static final Object LOCK_1 = new Object();
	private static final Object LOCK_2 = new Object();

	public static void main(String[] args) {
		CountDownLatch launcher = new CountDownLatch(1);
		Thread1 t1 = new Thread1(launcher);
		Thread2 t2 = new Thread2(launcher);
		t1.start();
		t2.start();
		launcher.countDown();
	}

	private static class Thread1 extends Thread {
		private final CountDownLatch launcher;

		public Thread1(CountDownLatch launcher) {
			this.launcher = launcher;
		}

		@Override
		public void run() {
			synchronized (LOCK_1) {
				System.out.println("Thread 1: Lock1 blocked");

				try {
					launcher.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


				synchronized (LOCK_2) {
					System.out.println("unreachable block");
				}
			}
		}
	}

	private static class Thread2 extends Thread {
		private final CountDownLatch launcher;

		public Thread2(CountDownLatch launcher) {
			this.launcher = launcher;
		}

		@Override
		public void run() {
			synchronized (LOCK_2) {
				System.out.println("Thread 2: Lock2 blocked");
				try {
					launcher.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (LOCK_1) {
					System.out.println("unreachable block");
				}
			}
		}
	}
}
