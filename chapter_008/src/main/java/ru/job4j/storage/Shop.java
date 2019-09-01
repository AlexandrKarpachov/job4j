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
 * Shop storage
 */
public class Shop implements Storage {
	private double discount;

	public Shop(double discount) {
		this.discount = discount;
	}

	List<Food> products = new ArrayList<>();

	@Override
	public void add(Food food) {
		products.add(food);
	}

	@Override
	public boolean accept(Food food) {
		var result = false;
		var existingPercent = food.getTimeOfExisting();
		if (existingPercent > 25 && existingPercent < 75) {
			result = true;
		} else if (existingPercent >= 75 && existingPercent < 100) {
			food.setDiscount(discount);
			result = true;
		}
		return result;
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}
}
