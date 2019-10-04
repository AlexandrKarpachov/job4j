package ru.job4j.bomberman;


import java.util.Objects;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class Cell {
	private final int x;
	private final int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Cell)) {
			return false;
		}
		Cell cell = (Cell) o;
		return x == cell.x
				&& y == cell.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
