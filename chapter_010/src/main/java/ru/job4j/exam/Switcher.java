package ru.job4j.exam;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 10.10.2019
 */
public class Switcher {

	public static void main(String[] args) throws InterruptedException {
		Selector selector = new Selector(2);
		Store store = new Store();
		var first = new Adder(1, store, selector);
		var second = new Adder(2, store, selector);
		first.setDaemon(true);
		second.setDaemon(true);
		first.start();
		second.start();
		Thread.sleep(2000);
		System.out.println(store.getStore());
	}
}

class Adder extends Thread {
	private int num;
	private final Store store;
	private final Selector switcher;

	public Adder(int num, Store store, Selector switcher) {
		this.num = num;
		this.store = store;
		this.switcher = switcher;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			synchronized (switcher) {
				if (switcher.get() + 1 == num) {
					for (int i = 0; i < 10; i++) {
						this.store.add(this.num);
					}
					switcher.toggle();
				}
			}
		}
	}
}