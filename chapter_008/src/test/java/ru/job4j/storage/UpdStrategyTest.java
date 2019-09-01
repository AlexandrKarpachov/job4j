package ru.job4j.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.storage.food.Cheese;
import ru.job4j.storage.food.Milk;
import ru.job4j.storage.food.Vegetables;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UpdStrategyTest {
	private Trash trash;
	private Shop shop;
	private WarehouseTwo warehouse;
	private RecycleStorage recycleStorage;
	private Fridge fridge;
	private ControlQuality controller;
	private static final double DISCOUNT = 10;

	@Before
	public void init() {
		this.trash = new Trash();
		this.shop = new Shop(DISCOUNT);
		this.warehouse = new WarehouseTwo();
		this.recycleStorage = new RecycleStorage();
		this.fridge = new Fridge();
		this.controller = new ControlQuality();
	}

	/*
	@Test
	public void whenAddExpiredFoodThenItDeliveredToTrash() {
		var foodList = List.of(
				new Cheese("sheese", this.getDate(-12), this.getDate(-2)),
				new Cheese("sheese2", this.getDate(-13), this.getDate(-3)),
				new Milk("Milk", this.getDate(-15), this.getDate(-5))
		);

		this.controller.distributeAll(foodList);

		assertThat(this.trash.getProductList(), is(foodList));
	}

	@Test
	public void whenAddFreshFoodThenItDeliveredToWarehouse() {
		var foodList = List.of(
				new Cheese("sheese", this.getDate(-1), this.getDate(13)),
				new Milk("Milk", this.getDate(-2), this.getDate(15))
		);

		this.controller.distributeAll(foodList);

		assertThat(this.warehouse.getProductList(), is(foodList));
	}

	@Test
	public void whenAddNormalFoodThenItDeliveredToShop() {
		var foodList = List.of(
				new Cheese("sheese", this.getDate(-12), this.getDate(13)),
				new Milk("Milk", this.getDate(-20), this.getDate(15))
		);

		this.controller.distributeAll(foodList);

		assertThat(this.shop.getProductList(), is(foodList));
	}

	@Test
	public void whenAddExpiredFoodWithRecycleOptionThenItDeliveredToRecycleStorage() {
		var food = new Cheese("sheese", this.getDate(-12), this.getDate(-1), true);

		this.controller.distribute(food);

		assertThat(this.recycleStorage.getProductList().get(0), is(food));
	}

	@Test
	public void whenAddFreshVegetablesThenItDeliveredToFridge() {
		var food = new Vegetables("potato", this.getDate(-1), this.getDate(14));

		this.controller.distribute(food);

		assertThat(this.fridge.getProductList().get(0), is(food));
	}

	@Test
	public void whenAddCloseToExperienceDateFoodThenItDeliveredToShopAndSetDiscount() {
		var food = new Cheese("sheese", this.getDate(-12), this.getDate(1));

		this.controller.distribute(food);

		assertThat(food.getDiscount(), is(DISCOUNT));
	}


	 */

	/**
	 * The method adds days to the current date.
	 * @param day amount of day which will be added to current time.
	 * @return Date in of milliseconds since January 1, 1970, 00:00:00 GMT
	 */
	private long getDate(int day) {
		Calendar calendar = Calendar.getInstance();
		var currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, currentDay + day);
		return calendar.getTime().getTime();
	}


}