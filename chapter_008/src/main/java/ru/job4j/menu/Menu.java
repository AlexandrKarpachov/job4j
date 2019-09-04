package ru.job4j.menu;

import java.util.*;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 04.09.2019
 */
public class Menu {
	private String name;
	private Map<String, MenuItem> items = new HashMap<>();

	public Menu(String name) {
		this.name = name;
	}

	public Menu(String name, List<MenuItem> items) {
		this.name = name;
		this.addItems(items);
	}

	public String show() {
		var result = new StringBuilder();
		result.append(name);
		result.append(System.lineSeparator());
		for (MenuItem item: this.items.values()) {
			result.append(item.show());
		}
		return result.toString();
	}

	public void addItem(MenuItem item) {
		items.put(item.getKey(), item);
	}

	public MenuItem addItem(String key, String name) {
		var item = new MenuItem(key, name);
		this.addItem(item);
		return item;
	}

	public void addItems(List<MenuItem> items) {
		items.forEach(this::addItem);
	}

	public MenuItem getItem(String key) {
		MenuItem result = null;
		for (MenuItem item: this.items.values()) {
			result = item.getItem(key);
			if (result != null) {
				break;
			}
		}
		return result;
	}

}
