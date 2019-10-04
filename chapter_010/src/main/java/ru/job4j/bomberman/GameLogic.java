package ru.job4j.bomberman;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class GameLogic {
	private final Board board;
	private final ExecutorService pool = Executors.newFixedThreadPool(2);
	private static final int SIZE = 10;

	public GameLogic(Board board) {
		this.board = board.init();
	}

	public static void main(String[] args) {
		var logic = new GameLogic(new Board(SIZE));
		var start = new Cell(0, 0);
		logic.pool.submit(logic.new PlayerRun(start, new Player(SIZE, start).init()));
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
			while (!Thread.currentThread().isInterrupted()) {
				var next = this.player.getNextMove();
				var wasMoved = board.move(current, next);
				while (!wasMoved) {
					next = player.getAnotherMove(next);
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
