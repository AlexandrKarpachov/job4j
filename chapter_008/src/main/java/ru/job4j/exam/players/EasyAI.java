package ru.job4j.exam.players;

import ru.job4j.exam.Board;
import ru.job4j.exam.Coordinate;

import java.util.Random;

public class EasyAI extends Player {

	public EasyAI() {
	}

	public EasyAI(String name) {
		super(name);
	}

	public EasyAI(String name, Board board) {
		super(name, board);
	}

	@Override
	public Coordinate genNextMove() {
		var random = new Random();
		Coordinate result;
		do {
			int bound = super.board.getSize();
			int x = random.nextInt(bound);
			int y = random.nextInt(bound);
			result = new Coordinate(x, y);
		} while (super.board.isWrong(result));
		return result;
	}





}
