package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarehouseTwo implements Storage {
	private Warehouse warehouse;

	public WarehouseTwo(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	private List<Food> products = new ArrayList<>();

	@Override
	public void add(Food food) {
		if (warehouse.isFull()) {
			products.add(food);
		} else {
			this.warehouse.add(food);
		}
	}

	@Override
	public boolean accept(Food food) {
		return this.warehouse.accept(food);
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}
}
