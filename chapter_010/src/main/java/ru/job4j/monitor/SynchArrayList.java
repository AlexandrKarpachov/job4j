package ru.job4j.monitor;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.CustomArrayList;

import java.util.Iterator;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 30.09.2019
 */

@ThreadSafe
public class  SynchArrayList<T> implements Iterable<T> {
	@GuardedBy("this")
	CustomArrayList<T> list = new CustomArrayList<>();

	public synchronized void add(T item) {
		this.list.add(item);
	}

	public synchronized void get(int index) {
		this.list.get(index);
	}

	private synchronized CustomArrayList<T> copy() {
		var result = new CustomArrayList<>();
		list.forEach(result::add);
		return new CustomArrayList<>();
	}

	@Override
	public Iterator<T> iterator() {
		return this.copy().iterator();
	}
}
