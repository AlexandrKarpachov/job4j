package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fridge implements Storage {
	private List<Food> products = new ArrayList<>();
	/**
	 * celsius temperature
	 */
	private double temperature = 5;

	@Override
	public void add(Food food) {
		products.add(food);
	}

	@Override
	public boolean accept(Food food) {
		return false;
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
