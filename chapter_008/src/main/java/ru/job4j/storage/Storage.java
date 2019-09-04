package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.List;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 29.08.2019
 *
 * interface for stores
 */
public interface Storage {
	void add(Food food);

	boolean accept(Food food);

	List<Food> getProductList();

	List<Food> removeFood();
}
