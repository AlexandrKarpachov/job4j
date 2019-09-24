package ru.job4j.seabatle;

import java.util.List;

public interface Ship {
	int getSize();

	void reserveCells(List<Cell> cells);

	boolean isHit(Cell cell);

	boolean isDead();
}
