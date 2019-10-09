package ru.job4j.bomberman;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class GameLogic {
	private final Board board;
	private final ExecutorService pool = Executors.newCachedThreadPool();
	private final int size;
	private final int monsterAmount;
	private final List<Cell> obstacles;

	public GameLogic(Board board, int size, int monsterAmount, List<Cell> obstacles) {
		this.board = board.init();
		this.size = size;
		this.monsterAmount = monsterAmount;
		this.obstacles = obstacles;
	}

	public static void main(String[] args) {
		int size = 15;
		int monsters = 5;
		List<Cell> obstacles = List.of(new Cell(1, 4), new Cell(2, 4));
		var logic = new GameLogic(new Board(size), size, monsters, obstacles);
		logic.run();
	}

	public void run() {
		for (Cell obs : obstacles) {
			this.board.addObstacle(obs);
		}
		var pStart = new Cell(0, 0);
		this.pool.submit(this.new PlayerRun(pStart, new Player(size, pStart)));
		for (int i = 0; i < this.monsterAmount; i++) {
			var mStart = this.random();
			this.pool.submit(this.new MonsterRun(mStart, new Monster(size, mStart).init()));
		}
	}

	private Cell random() {
		Cell result;
		Random rnd = new Random();
		do {
			var x = rnd.nextInt(this.size);
			var y = rnd.nextInt(this.size);
			result = new Cell(x, y);
		} while (this.board.isLocked(result));
		return result;
	}

	private class PlayerRun implements Runnable {
		private Cell current;
		private final Player player;

		public PlayerRun(Cell current, Player player) {
			this.current = current;
			this.player = player;
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted())	{
				var next = this.player.getNextMove();
				if (next != null) {
					board.move(current, next);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	private class MonsterRun implements Runnable {
		private Cell current;
		private final Monster monster;

		public MonsterRun(Cell current, Monster monster) {
			this.current = current;
			this.monster = monster;
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				var next = this.monster.getNextMove();
				var wasMoved = board.move(current, next);
				while (!wasMoved) {
					next = monster.getAnotherMove(next);
					wasMoved = board.move(current, next);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
