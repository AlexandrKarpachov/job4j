package ru.job4j.storage;

import ru.job4j.storage.food.Food;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 29.08.2019
 *
 * Class which distributes Food in such way:
 * If the expiration date is less than 25%, send to Warehouse.
 * If the expiration date from 25% to 75% is sent to the Shop.
 * If the expiration date is more than 75%, then put a discount on the product and send it to the Shop
 * If the expiration date has expired. Send the product to the trash.
 */
public class MainStrategy implements DistributionStrategy {
	/**
	 * Shop storage
	 */
	private Storage shop;
	/**
	 * Ware house storage.
	 */
	private Storage wareHouse;
	/**
	 * storage for trash.
	 */
	private Storage trash;
	/**
	 * discount on a products whose a near to expiration date.
	 */
	private double discount;

	public MainStrategy(Storage shop, Storage wareHouse, Storage trash, double discount) {
		this.shop = shop;
		this.wareHouse = wareHouse;
		this.trash = trash;
		this.discount = discount;
	}

	@Override
	public void distribute(Food product) {
		var percent = product.getTimeOfExisting();
		if (percent < 25) {
			wareHouse.add(product);
		} else if (percent < 75) {
			shop.add(product);
		} else if (percent < 100) {
			product.setDiscount(discount);
			shop.add(product);
		} else {
			trash.add(product);
		}
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
