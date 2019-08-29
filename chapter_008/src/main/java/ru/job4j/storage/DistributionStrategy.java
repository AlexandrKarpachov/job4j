package ru.job4j.storage;

import ru.job4j.storage.food.Food;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 29.08.2019
 *
 * interface for strategies
 */
public interface DistributionStrategy {
	/**
	 * The method distributes food to different stores
	 * @param product food for distributing.
	 */
	void distribute(Food product);
}
