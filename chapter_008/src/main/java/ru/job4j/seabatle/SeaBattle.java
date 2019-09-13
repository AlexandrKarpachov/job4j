package ru.job4j.seabatle;

import java.util.List;
import java.util.Random;

public class SeaBattle {
	private Player player1;
	private Player player2;
	private Player attacker;

	/** stores sizes of all ship */
	private final static List<Integer> SHIP_SET = List.of(
			4, 3, 3, 2, 2, 2, 1, 1, 1, 1
	);

	public SeaBattle(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void run() {
		this.shipArrangement();
		this.askFirstMove();
		Player defender;
		while (!this.player1.isLose() && !this.player2.isLose()) {
			defender = this.getNextPlayer();
			var isHit = false;
			do {
				var shoot = this.attacker.getNextMove();
				isHit = defender.bombard(shoot);
			} while (isHit);
			this.attacker = this.getNextPlayer();
		}
		var winner = this.player1.isLose() ? this.player2 : this.player1;
		this.showWinner(winner);
	}

	/**
	 * Method arranges players ship using their inner realisation of this functional.
	 */
	private void shipArrangement() {
		if (player1.getBoardSize() != player2.getBoardSize()) {
			throw new RuntimeException("different sizes");
		}
		for (Integer ship: SHIP_SET) {
			player1.arrange(ship);
			player2.arrange(ship);
		}
	}

	/** Method must ask who will start the battle. */
	private void askFirstMove() {
		var rnd = new Random();
		this.attacker = rnd.nextInt(100) < 50
				? this.player1 : this.player2;
	}

	/** realization of winning scenario */
	private void showWinner(Player player) {
		System.out.println(player + " win");
	}

	private Player getNextPlayer() {
		return this.attacker == this.player1
				? this.player2 : this.player1;
	}
}
