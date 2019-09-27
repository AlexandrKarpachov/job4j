package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 27.09.2019
 */
@ThreadSafe
public class Count {
	@GuardedBy("this")
	private int value;

	public synchronized void increment() {
		this.value++;
	}

	public synchronized int get() {
		return this.value;
	}
}
