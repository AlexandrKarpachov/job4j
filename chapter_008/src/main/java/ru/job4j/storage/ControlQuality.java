package ru.job4j.storage;

import ru.job4j.storage.food.Food;

import java.util.List;

public class ControlQuality {

	private DistributionStrategy strategy;

	public ControlQuality(DistributionStrategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(DistributionStrategy strategy) {
		this.strategy = strategy;
	}

	public void distribute(Food product) {
		this.strategy.distribute(product);
	}

	public void distributeAll(List<Food> products) {
		products.forEach(this::distribute);
	}

	public static void main(String[] args) {

	}
}
