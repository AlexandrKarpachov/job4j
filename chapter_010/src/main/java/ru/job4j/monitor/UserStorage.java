package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for user storing and processing.
 *
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 28.09.2019
 */
@ThreadSafe
public class UserStorage {
	@GuardedBy("this")
	private Map<Integer, User> store = new HashMap<>();


	public synchronized boolean add(User user) {
		return store.putIfAbsent(user.id, user) == null;
	}

	public synchronized boolean update(int userID, int amount) {
		var result = false;
		var user = this.store.get(userID);
		if (user != null && user.amount + amount > 0) {
			user.amount += amount;
			result = true;
		}
		return result;
	}

	public synchronized boolean delete(User user) {
		return this.store.remove(user.id) != null;
	}


	public synchronized boolean transfer(int fromId, int toId, int amount) {
		var result = false;
		var supplier = this.store.get(fromId);
		var consumer = this.store.get(toId);
		if (supplier != null && consumer != null
			&& supplier.amount >= amount) {
			supplier.amount -= amount;
			consumer.amount += amount;
			result = true;
		}
		return result;
	}
}
