package ru.job4j.exam;

import ru.job4j.exam.players.EasyAI;
import ru.job4j.exam.players.Player;

public class Main {
	private final static int WIN_SCORE = 5;
	private Board board;
	private Player player1;
	private Player player2;
	private Player current;

	public Main(Board board, Player player1, Player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.current = player1;
	}

	/**
	 * Asks player for next move and checks it.
	 * @param player player.
	 * @return coordinate from player.
	 */
	public Coordinate getNextMove(Player player) {
		var result =  player.genNextMove();
		if (board.getByCoordinate(result) == null || board.isWrong(result)) {
			throw new WrongCoordinateException(String.format(
					"Player %s returned wrong coordinate", player.getName()
			));
		}
		return result;
	}

	/**
	 * The method prepares classes to working state.
	 */
	public Main init() {
		this.board.fillBoard();
		this.player1.setFigureMark(true);
		this.player2.setFigureMark(false);
		this.player1.setBoard(this.board);
		this.player2.setBoard(this.board);
		return this;
	}

	public static void main(String[] args) {
		var board = new Board(3);
		var run = new Main(board,
				new EasyAI("first"),
				new EasyAI("second")
		).init();
		run.run();
	}

	/**
	 * Runs the game.
	 */
	public void run() {
		Player curPlayer;
		while (player1.getScore() < WIN_SCORE && player2.getScore() < WIN_SCORE) {
			this.init();
			var player1Win = false;
			var player2Win = false;
			while (!player1Win && !player2Win && board.hasGap()) {
				curPlayer = this.nextPlayer();
				var coord = this.getNextMove(curPlayer);
				this.board.setFigure(coord, curPlayer.isX());
				System.out.println(this.board.show());
				player1Win = board.isWinnerX();
				player2Win = board.isWinnerO();
			}
			this.printResult(player1Win, player2Win);
		}
		var winner = player1.getScore() > player2.getScore() ? player1 : player2;
		System.out.println(winner.getName() + " win the Game! ");
	}

	/**
	 * Prints result of the round.
	 * @param firstWin true if first player win.
	 * @param secondWin true if second player win.
	 */
	private void printResult(boolean firstWin, boolean secondWin) {
		if (firstWin) {
			this.player1.increaseScore();
			System.out.println(player1.getName() + " win round");

		} else if (secondWin) {
			this.player2.increaseScore();
			System.out.println(player1.getName() + " win round");
		} else {
			System.out.println("draw");
		}
		var currentScore = String.format(
				"Current score:%n %s %d : %d %s",
				this.player1.getName(), this.player1.getScore(),
				this.player2.getScore(), this.player2.getName()
		);
		System.out.println(currentScore);
	}

	/**
	 * returns current and then toggles it.
	 */
	private Player nextPlayer() {
		var result = current;
		if (current == player1) {
			current = player2;
		} else {
			current = player1;
		}
		return result;
	}
}
