package ru.job4j.exam;

public class Figure {
	private boolean markX = false;
	private boolean markO = false;
	private Coordinate coordinate;

	public Figure(Coordinate coordinate) {
		this.coordinate = coordinate;
	}


	void take(boolean markX) {
		this.markX = markX;
		this.markO = !markX;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public boolean hasMarkX() {
		return this.markX;
	}

	public boolean hasMarkO() {
		return this.markO;
	}

	public boolean isEmpty() {
		return this.markO == this.markX;
	}

	public String lastLine() {
		return this.text(" ");
	}

	public String commonLine() {
		return this.text("_");
	}

	private String text(String def) {
		var result = def;
		if (this.markX) {
			result = "X";
		} else if (this.markO) {
			result = "O";
		}
		return result;
	}
}
