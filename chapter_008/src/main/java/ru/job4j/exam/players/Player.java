package ru.job4j.exam.players;

import ru.job4j.exam.Board;
import ru.job4j.exam.Coordinate;

public abstract class Player {
	protected String name = "Easy";
	protected int score = 0;
	protected boolean marX;
	protected Board board;

	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public Player(String name, Board board) {
		this.name = name;
		this.board = board;
	}

	abstract public Coordinate genNextMove();

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public void increaseScore() {
		this.score++;
	}

	public void setFigureMark(boolean x) {
		this.marX = x;
	}

	public boolean isX() {
		return marX;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
