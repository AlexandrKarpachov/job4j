package ru.job4j.storage.food;

public class Milk extends Food {
	public Milk(String name, long createdDate, long experienceDate) {
		super(name, createdDate, experienceDate);
	}

	public Milk(String name, long createdDate, long experienceDate, boolean canRecycle) {
		super(name, createdDate, experienceDate, canRecycle);
	}

	public Milk(String name, long createdDate, long experienceDate, double discount) {
		super(name, createdDate, experienceDate, discount);
	}

	public Milk(String name, long createdDate, long experienceDate, double discount, boolean canRecycle) {
		super(name, createdDate, experienceDate, discount, canRecycle);
	}
}
