package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecycleStorage implements Storage {
	private Trash trash;
	private List<Food> products = new ArrayList<>();

	public RecycleStorage(Trash trash) {
		this.trash = trash;
	}

	@Override
	public void add(Food food) {
		if (food.isCanRecycle()) {
			this.products.add(food);
		} else {
			trash.add(food);
		}
	}

	@Override
	public boolean accept(Food food) {
		return this.trash.accept(food);
	}

	@Override
	public List<Food> getProductList() {
		return Collections.unmodifiableList(this.products);
	}

	@Override
	public List<Food> removeFood() {
		List<Food> result = new ArrayList<>(this.products);
		result.addAll(this.trash.removeFood());
		this.products.clear();
		return result;
	}
}
