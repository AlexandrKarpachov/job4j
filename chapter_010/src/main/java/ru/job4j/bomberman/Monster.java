package ru.job4j.bomberman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class Monster {
	private static final int UPWARD = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	private static final int DOWN = 3;
	private final int size;
	private Cell startPos;
	private Map<Integer, Supplier<Cell>> moves = new HashMap<>();

	public Monster(int size, Cell startPos) {
		this.size = size;
		this.startPos = startPos;

	}

	public Monster init() {
		var move = new Move();
		this.moves.put(UPWARD, move::up);
		this.moves.put(LEFT, move::left);
		this.moves.put(RIGHT, move::right);
		this.moves.put(DOWN, move::down);
		return this;
	}

	public Cell getNextMove() {
		Cell result;
		Random random = new Random();
		do {
			var move = random.nextInt(4);
			result = moves.get(move).get();
		} while (!this.validate(result));
		this.startPos = result;
		return result;
	}

	public Cell getAnotherMove(Cell last) {
		Cell result = this.getNextMove();
		while (result.equals(last)) {
			result = this.getNextMove();
		}
		return result;
	}

	private boolean validate(Cell cell) {
		return cell.getX() >= 0 && cell.getY() >= 0
				&& cell.getX() < size && cell.getY() < size;
	}

	private class Move {
		public Cell up() {
			return new Cell(startPos.getX(), startPos.getY() - 1);
		}

		public Cell down() {
			return new Cell(startPos.getX() + 1, startPos.getY());
		}

		public Cell right() {
			return new Cell(startPos.getX(), startPos.getY() + 1);
		}

		public Cell left() {
			return new Cell(startPos.getX() - 1, startPos.getY());
		}
	}
}
