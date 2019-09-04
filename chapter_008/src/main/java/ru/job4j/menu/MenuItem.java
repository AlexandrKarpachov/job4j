package ru.job4j.menu;

import java.util.*;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 04.09.2019
 */
public class MenuItem {
	private int nestingLevel = 0;
	private String key;
	private String name;
	private Map<String, MenuItem> innerItems = new LinkedHashMap<>();
	private ActionListener action;

	public MenuItem(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public MenuItem(String key, String name, ActionListener action) {
		this.key = key;
		this.name = name;
		this.action = action;
	}

	public void addItem(MenuItem item) {
		innerItems.put(item.getKey(), item);
	}

	public MenuItem addItem(String key, String name) {
		var item = new MenuItem(key, name);
		this.addItem(item);
		return item;
	}

	public void addItems(List<MenuItem> items) {
		items.forEach(this::addItem);
	}

	public void perform() {
		this.action.performAction();
	}

	private void setNestingLevel() {
		Deque<MenuItem> stack = new ArrayDeque<>();
		stack.push(this);
		while (!stack.isEmpty()) {
			var current = stack.poll();
			for (MenuItem item: current.innerItems.values()) {
				item.nestingLevel = current.nestingLevel + 1;
				stack.push(item);
			}
		}
	}

	String show() {
		this.setNestingLevel();
		var result = new StringBuilder();
		Deque<MenuItem> stack = new ArrayDeque<>();
		stack.push(this);
		while (!stack.isEmpty()) {
			var current = stack.poll();
			result.append("\t".repeat(Math.max(0, current.nestingLevel)));
			result.append(String.join(" ", current.key, current.name));
			result.append(System.lineSeparator());
			List<MenuItem> reverseOrderedItems = new ArrayList<>(current.innerItems.values());
			Collections.reverse(reverseOrderedItems);
			for (MenuItem item: reverseOrderedItems) {
				stack.push(item);
			}
		}
		return result.toString();
	}

	MenuItem getItem(String key) {
		MenuItem result = null;
		Deque<MenuItem> stack = new ArrayDeque<>();
		stack.push(this);
		while (!stack.isEmpty()) {
			var current = stack.poll();
			if (current.getKey().equalsIgnoreCase(key)) {
				result = current;
				break;
			}
			for (MenuItem item: current.innerItems.values()) {
					stack.push(item);
			}
		}
		return result;
	}

	public void setAction(ActionListener actionListener) {
		this.action = actionListener;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
