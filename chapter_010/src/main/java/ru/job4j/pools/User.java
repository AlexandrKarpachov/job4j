package ru.job4j.pools;

import java.util.Objects;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class User {
	private String name;
	private String email;

	public User(String name, String eMail) {
		this.name = name;
		this.email = eMail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
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
		return Objects.equals(name, user.name)
				&& Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, email);
	}
}
