package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.storage.food.Food;

import java.util.List;

public class ControlQuality {
	private List<Storage> stores;
	private static final Logger LOG = LoggerFactory.getLogger(ControlQuality.class);

	public ControlQuality(List<Storage> stores) {
		this.stores = stores;
	}

	public ControlQuality() {
	}

	public void addStore(Storage storage) {
		this.stores.add(storage);
	}


	public void distribute(Food food) {
		var isDistributed = false;
		for (Storage storage : this.stores) {
			if (storage.accept(food)) {
				storage.add(food);
				isDistributed = true;
				break;
			}
		}
		if (!isDistributed) {
			LOG.error(String.format(
					"%s was not distributed. Not suitable stores",
					food.toString()
			));
		}
	}

	public void distributeAll(List<Food> food) {
		food.forEach(this::distribute);
	}

	public void recycle() {
		for (Storage storage: this.stores) {
			var temp = storage.removeFood();
			this.distributeAll(temp);
		}
	}

}
