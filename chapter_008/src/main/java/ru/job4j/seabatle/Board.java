package ru.job4j.seabatle;

public interface Board {
	boolean addShip(Ship ship);

	/** Fills board by new {@link ru.job4j.seabatle.Cell} */
	void init();

	boolean bombard(Coordinate coordinate);

	boolean isBombarded(Coordinate coordinate);

	int getBoardSize();

	Ship getShip(Coordinate coordinate);
}
