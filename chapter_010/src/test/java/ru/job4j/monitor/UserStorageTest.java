package ru.job4j.monitor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {
	UserStorage storage;

	@Before
	public void init() {
		this.storage = new UserStorage();
	}

	@Test
	public void whenDeleteNonExistsUserThenFalse() {
		var result = this.storage.delete(new User(1, 0));

		assert (!result);
	}

	@Test
	public void whenAddDeleteThenReturnTrue() {
		var user = new User(1, 0);
		this.storage.add(user);

		var result = this.storage.delete(new User(1, 0));

		assert (result);
	}

	@Test
	public void whenAttendToAddUserWithTheSameIDThenReturnFalse() {
		var user = new User(1, 0);
		var userDuplicate = new User(1, 10);

		this.storage.add(user);
		var result = this.storage.add(userDuplicate);

		assert (!result);
	}

	@Test
	public void whenAttendToMakeNegativeBalanceThenReturnFalse() {
		var user = new User(1, 0);

		this.storage.add(user);
		var result = this.storage.update(1, -10);

		assert (!result);
	}

	@Test
	public void whenAttendToMakeIllegalTransfer() {
		var supplier = new User(1, 0);
		var consumer = new User(2, 10);
		this.storage.add(supplier);
		this.storage.add(consumer);

		var result = this.storage.transfer(supplier.id, consumer.id, 15);

		assert (!result);
	}

	@Test
	public void whenMakeTransferThenCorrectAmountsChange() {
		var supplier = new User(1, 20);
		var consumer = new User(2, 0);
		this.storage.add(supplier);
		this.storage.add(consumer);

		this.storage.transfer(supplier.id, consumer.id, 15);

		assertThat(supplier.amount, is(5));
		assertThat(consumer.amount, is(15));
	}

}