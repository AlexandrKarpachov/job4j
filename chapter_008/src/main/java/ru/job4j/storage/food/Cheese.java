package ru.job4j.storage.food;

public class Cheese extends Food {
	public Cheese(String name, long createdDate, long experienceDate) {
		super(name, createdDate, experienceDate);
	}

	public Cheese(String name, long createdDate, long experienceDate, double discount) {
		super(name, createdDate, experienceDate, discount);
	}
}
