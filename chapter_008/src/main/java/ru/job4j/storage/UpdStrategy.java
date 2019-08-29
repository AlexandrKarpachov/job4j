package ru.job4j.storage;

import ru.job4j.storage.food.Food;

public class UpdStrategy implements DistributionStrategy {
	private Storage recycleStorage;
	private Storage fridge;
	private double discount = 0;
	private MainStrategy oldStrategy;

	public UpdStrategy(Storage shop, Storage warehouse, Storage trash, Storage recycleStorage, Storage fridge) {
		this.recycleStorage = recycleStorage;
		this.fridge = fridge;
		this.oldStrategy = new MainStrategy(shop, warehouse, trash, discount);
	}

	@Override
	public void distribute(Food product) {
		if (product.getExperienceDate() <= System.currentTimeMillis()
			&& product.isCanRecycle()) {
			this.recycleStorage.add(product);
		} else if (product.getTimeOfExisting() < 25
			&& product.isNeedFridge()) {
			this.fridge.add(product);
		} else {
			this.oldStrategy.distribute(product);
		}
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
		this.oldStrategy.setDiscount(discount);
	}
}
