package ru.job4j.seabatle;

public interface Board {
	void addShip(Ship ship);

	/** Fills board by new {@link ru.job4j.seabatle.Cell} */
	void init();

	void bombard(int x, int y);

	boolean isBombarded(Coordinate coordinate);
}
