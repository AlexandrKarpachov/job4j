package ru.job4j.nonbloking;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 01.10.2019
 */
public class Base {
	private int id;
	private int version = 0;

	public Base(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


}
