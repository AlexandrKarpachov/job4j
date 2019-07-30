package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class TrackerSQLTest {
	List<Item> items;
	TrackerSQL tracker;

	@Before
	public void init() {
		this.items = new ArrayList<>();
		this.tracker = new TrackerSQL();
		this.tracker.init();
	}

	@After
	public void close() {
		items.stream().map(Item::getId).forEach(this.tracker::delete);
		tracker.close();
	}

	@Test
	public void checkConnection() {
		TrackerSQL sql = new TrackerSQL();
		assertThat(sql.init(), is(true));
	}


	@Test
	public void whenAddNewItemThenTrackerHasSameItem() {
		var item = new Item("test1", "testDescription", System.currentTimeMillis());
		this.items = List.of(item);

		this.tracker.add(item);
		var id = item.getId();
		Item result = tracker.findById(id);

		assert (result.equals(item));

	}

	@Test
	public void whenReplaceThenReturnUpdatedItem() {
		Item previous = new Item("test1", "testDescription", System.currentTimeMillis());
		Item next = new Item("test2", "testDescription2", System.currentTimeMillis());
		this.items = List.of(previous);


		this.tracker.add(previous);
		next.setId(previous.getId());
		this.tracker.replace(previous.getId(), next);

		assertThat(this.tracker.findById(previous.getId()), is(next));
	}


	@Test
	public void whenDeleteByIdThenCanNotFindItem() {
		var item = new Item("test1", "testDescription", System.currentTimeMillis());

		this.tracker.add(item);
		this.tracker.delete(item.getId());

		assertThat(tracker.findById(item.getId()), is(nullValue()));
	}


	@Test
	public void whenFindAllThenReturnListWithAllValues() {
		var first = new Item("test1", "testDescription", System.currentTimeMillis());
		var second = new Item("test2", "testDescription2", System.currentTimeMillis());
		var third = new Item("test3", "testDescription4", System.currentTimeMillis());
		this.items = List.of(first, second, third);

		this.tracker.add(first);
		this.tracker.add(second);
		this.tracker.add(third);
		var actual = tracker.findAll();

		assertThat(items, is(actual));

	}

	@Test
	public void whenFindNameThenReturnArrayDuplicates() {
		var first = new Item("test3", "testDescription", System.currentTimeMillis());
		var second = new Item("test3", "testDescription2", System.currentTimeMillis());
		var third = new Item("test3", "testDescription4", System.currentTimeMillis());
		this.items = List.of(first, second, third);

		tracker.add(first);
		tracker.add(second);
		tracker.add(third);
		var actual = tracker.findByName("test3");

		assertThat(this.items, is(actual));
	}
}