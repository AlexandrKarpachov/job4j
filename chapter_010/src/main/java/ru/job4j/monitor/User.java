package ru.job4j.monitor;


import java.util.Objects;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 28.09.2019
 */
public class User {
	int id;
	int amount;

	public User(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return id == user.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return String.format("User{id=%d, amount=%d", id, amount);
	}
}
