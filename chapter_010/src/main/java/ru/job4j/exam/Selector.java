package ru.job4j.exam;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 10.10.2019
 */
public class Selector {
	private int position = 0;
	private final int bound;

	public Selector(int bound) {
		this.bound = bound;
	}

	public synchronized void toggle() {
		if (this.position < bound - 1) {
			this.position++;
		} else {
			position = 0;
		}
	}

	public synchronized int get() {
		return position;
	}
}