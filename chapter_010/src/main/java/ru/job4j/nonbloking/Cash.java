package ru.job4j.nonbloking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 01.10.2019
 */
public class Cash {
	private ConcurrentHashMap<Integer, Base> storage = new ConcurrentHashMap<>();

	public void add(Base base) {
		this.storage.put(base.getId(), base);
	}

	public void update(Base base) {
		this.storage.computeIfPresent(
				base.getId(),
				(key, value) -> {
					base.setVersion(base.getVersion() + 1);
					if (value.getVersion() != base.getVersion() - 1) {
						throw new OptimisticException("Throw Exception in Thread");
					}
					return base;
				}
		);
	}

	public void delete(Base base) {
		this.storage.remove(base.getId(), base);
	}
}
