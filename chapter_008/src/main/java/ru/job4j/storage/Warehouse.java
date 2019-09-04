package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private int capacity = 100;
	private ArrayList<Food> products = new ArrayList<>();
	private static final Logger LOG = LoggerFactory.getLogger(ControlQuality.class);

	public Warehouse(int capacity) {
		this.capacity = capacity;
	}

	public Warehouse() {
	}

	public void setCapacity(int size) {
		if (size > products.size()) {
			capacity = size;
		} else {
			LOG.error("capacity can't be less then size of located food");
		}
	}

	public boolean isFull() {
		return products.size() >= capacity;
	}

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

	@Override
	public List<Food> removeFood() {
		List<Food> result = new ArrayList<>(this.products);
		this.products.clear();
		return result;
	}
}
