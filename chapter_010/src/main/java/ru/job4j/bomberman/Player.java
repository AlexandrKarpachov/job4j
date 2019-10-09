package ru.job4j.bomberman;

import java.util.*;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 08.10.2019
 */
public class Player {
	private final int size;
	private Cell startPos;
	private Queue<Cell> moves = new LinkedList<>();


	public Player(int size, Cell startPos) {
		this.size = size;
		this.startPos = startPos;
	}

	public Cell getNextMove() {
		return this.moves.poll();
	}

	public void up() {
		this.addMove(new Cell(startPos.getX(), startPos.getY() - 1));
	}

	public void down() {
		this.addMove(new Cell(startPos.getX() + 1, startPos.getY()));
	}

	public void right() {
		this.addMove(new Cell(startPos.getX(), startPos.getY() + 1));

	}

	public void left() {
		this.addMove(new Cell(startPos.getX() - 1, startPos.getY()));
	}

	private void addMove(Cell move) {
		if (this.validate(move)) {
			this.moves.add(move);
		}
	}

	protected boolean validate(Cell cell) {
		return cell.getX() >= 0 && cell.getY() >= 0
				&& cell.getX() < size && cell.getY() < size;
	}
}
