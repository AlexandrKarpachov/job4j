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
 * storage for trash.
 */
public class Trash implements Storage {
	private List<Food> products = new ArrayList<>();

	@Override
	public void add(Food food) {
		products.add(food);
	}

	@Override
	public boolean accept(Food food) {
		return food.getTimeOfExisting() >= 100;
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}

	@Override
	public List<Food> removeFood() {
		List<Food> result = new ArrayList<>(this.products);
		this.products.clear();
		return result;
	}
}
