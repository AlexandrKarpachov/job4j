package ru.job4j.seabatle;

public interface Cell {
	boolean isBombarded();

	void bombard();

	boolean isPartOfShip();

	Coordinate getCoordinates();
}
