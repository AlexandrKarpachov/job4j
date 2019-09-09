package ru.job4j.exam;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class Board {
	private Figure[][] table;
	private int size;

	public Board(int size) {
		this.size = size;
		this.table = new Figure[size][size];
	}

	/**
	 * @param predicate Predicate Function for Figures.
	 * @param startX    X Coordinate of Start Position.
	 * @param startY    Y Coordinate of Start Position.
	 * @param deltaX    Direction of changing X Coordinate.
	 * @param deltaY    Direction of changing Y Coordinate.
	 * @return true - if the column/row/diagonal is filled by the Figure X or The Figure O; otherwise - false;
	 */
	private boolean fillBy(Predicate<Figure> predicate, int startX, int startY, int deltaX, int deltaY) {
		boolean result = true;
		for (int index = 0; index != this.table.length; index++) {
			Figure cell = this.table[startX][startY];
			startX += deltaX;
			startY += deltaY;
			if (!predicate.test(cell)) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * Method checks if X won
	 */
	boolean isWinnerX() {
		return isDiagonal(Figure::hasMarkX) || isLine(Figure::hasMarkX);
	}

	/**
	 * Method checks if X won
	 */
	boolean isWinnerO() {
		return isDiagonal(Figure::hasMarkO) || isLine(Figure::hasMarkO);
	}

	/**
	 * Method checks to make sure if empty cells are left.
	 */
	boolean hasGap() {
		return Arrays.stream(this.table).flatMap(Arrays::stream)
				.anyMatch(Figure::isEmpty);
	}

	/**
	 * Method checks if  one of figures won by line or column.
	 * @param predicate that checks that the figure already exists
	 * @return true if figure won by line or vertical line, otherwise false
	 */
	private boolean isLine(Predicate<Figure> predicate) {
		boolean result = false;
		for (int i = 0; i < this.table.length; i++) {
			if (this.fillBy(predicate, i, 0, 0, 1)
					|| this.fillBy(predicate, 0, i, 1, 0)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Method checks if  one of figures won by line or column.
	 * @param figurePredicate that checks existing of current figure
	 * @return true if figure won by diagonal, otherwise false
	 */
	private boolean isDiagonal(Predicate<Figure> figurePredicate) {
		return this.fillBy(figurePredicate, this.table.length - 1, 0, -1, 1)
				|| this.fillBy(figurePredicate, 0, 0, 1, 1);
	}


	public boolean isWrong(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		return x >= size || y >= size || x < 0 || y < 0
				|| !this.getByCoordinate(coordinate).isEmpty();
	}

	public String show() {
		var result = new StringBuilder();
		StringJoiner line;
		for (int i = 0; i < this.size; i++) {
			line = new StringJoiner("|");
			for (int j = 0; j < this.size; j++) {
				if (i == this.size - 1) {
					line.add(table[i][j].lastLine());
				} else {
					line.add(table[i][j].commonLine());
				}
			}
			result.append(line.toString());
			result.append(System.lineSeparator());
		}
		return result.toString();
	}

	public Figure getByCoordinate(Coordinate coordinate) {
		Figure result;
		int x = coordinate.getX();
		int y = coordinate.getY();
		if (x < size && y < size) {
			result =  this.table[x][y];
		} else {
			result = null;
		}
		return result;
	}

	void fillBoard() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				this.table[i][j] = new Figure(new Coordinate(i, j));
			}
		}
	}

	public int getSize() {
		return size;
	}

	void setFigure(Coordinate coordinate, boolean x) {
		this.table[coordinate.getX()][coordinate.getY()].take(x);
	}

}
