package ru.job4j.storage.food;

public class Vegetables extends Food {
	public Vegetables(String name, long createdDate, long experienceDate) {
		super(name, createdDate, experienceDate);
		this.setNeedFridge(true);
	}

	public Vegetables(String name, long createdDate, long experienceDate, boolean canRecycle) {
		super(name, createdDate, experienceDate, canRecycle);
		this.setNeedFridge(true);
	}

	public Vegetables(String name, long createdDate, long experienceDate, double discount) {
		super(name, createdDate, experienceDate, discount);
		this.setNeedFridge(true);
	}

	public Vegetables(String name, long createdDate, long experienceDate, double discount, boolean canRecycle) {
		super(name, createdDate, experienceDate, discount, canRecycle);
		this.setNeedFridge(true);
	}
}
