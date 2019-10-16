package ru.job4j.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 10.10.2019
 */
public class Store {
	private String store = "";

	public synchronized void add(int i) {
		this.store += i;
	}

	public synchronized String getStore() {

		return store;
	}
}
