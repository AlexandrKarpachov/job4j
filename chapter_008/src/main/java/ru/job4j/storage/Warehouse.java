package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 29.08.2019
 *
 */
public class Warehouse implements Storage {

	private List<Food> products = new ArrayList<>();

	@Override
	public void add(Food food) {
		products.add(food);
	}

	@Override
	public boolean accept(Food food) {
		return food.getTimeOfExisting() < 25;
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}
}
