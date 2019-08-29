package ru.job4j.storage.food;

import java.util.Objects;
/**
* @author Aleksandr Karpachov
* @version $Id$
* @since 29.08.2019
*/
public class Food {
	private String name;
	private long createdDate;
	private long experienceDate;
	private double discount = 0;
	private boolean canRecycle = false;
	private boolean needFridge = false;


	public Food(String name, long createdDate, long experienceDate) {
		this.name = name;
		this.experienceDate = experienceDate;
		this.createdDate = createdDate;
	}

	public Food(String name, long createdDate, long experienceDate, boolean canRecycle) {
		this.name = name;
		this.createdDate = createdDate;
		this.experienceDate = experienceDate;
		this.canRecycle = canRecycle;
	}

	public Food(String name, long createdDate, long experienceDate, double discount) {
		this.name = name;
		this.experienceDate = experienceDate;
		this.createdDate = createdDate;
		this.discount = discount;
	}

	public Food(String name, long createdDate, long experienceDate, double discount, boolean canRecycle) {
		this.name = name;
		this.createdDate = createdDate;
		this.experienceDate = experienceDate;
		this.discount = discount;
		this.canRecycle = canRecycle;
	}

	/**
	 * The method calculates how many time gone from creating this item, as a percentage.
	 * for example: if Food was created 01.01.2019, experience date 21.01.2019, and current
	 *  date is 06.01.2019 then method will return 25.
	 * @return the ratio of the lifetime to its shelf life
	 */
	public double getTimeOfExisting() {
		var currentDate = System.currentTimeMillis();
		var shelfLife = this.experienceDate - this.createdDate;
		var survival = currentDate - this.createdDate;
		var percentValue = shelfLife / 100;
		return (double) survival / percentValue;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getExperienceDate() {
		return experienceDate;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public boolean isCanRecycle() {
		return canRecycle;
	}

	public void setCanRecycle(boolean canRecycle) {
		this.canRecycle = canRecycle;
	}

	public boolean isNeedFridge() {
		return needFridge;
	}

	public void setNeedFridge(boolean needFridge) {
		this.needFridge = needFridge;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Food)) {
			return false;
		}
		Food food = (Food) o;
		return experienceDate == food.experienceDate
				&& createdDate == food.createdDate
				&& Double.compare(food.discount, discount) == 0
				&& Objects.equals(name, food.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, experienceDate, createdDate, discount);
	}

}