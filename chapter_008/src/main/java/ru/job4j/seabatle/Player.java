package ru.job4j.seabatle;

public interface Player {

	boolean bombard(Coordinate coordinate);

	void arrange(int ship);

	int getBoardSize();

	boolean isLose();

	Coordinate getNextMove();
}
