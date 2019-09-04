package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fridge implements Storage {
	private Storage storage;
	private List<Food> products = new ArrayList<>();

	public Fridge(Storage storage) {
		this.storage = storage;
	}

	/**
	 * celsius temperature
	 */
	private double temperature = 5;

	@Override
	public void add(Food food) {
		if (food.isNeedFridge()) {
			this.products.add(food);
		} else {
			this.storage.add(food);
		}
	}

	@Override
	public boolean accept(Food food) {
		return storage.accept(food);
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}

	@Override
	public List<Food> removeFood() {
		List<Food> result = new ArrayList<>(this.products);
		result.addAll(this.storage.removeFood());
		this.products.clear();
		return result;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
