package ru.job4j.storage.food;

public class Milk extends Food {
	public Milk(String name, long createdDate, long experienceDate) {
		super(name, createdDate, experienceDate);
	}

	public Milk(String name, long createdDate, long experienceDate, double discount) {
		super(name, createdDate, experienceDate, discount);
	}
}
