package ru.job4j.exam.players;

import ru.job4j.exam.Board;
import ru.job4j.exam.Coordinate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsolePlayer extends Player {
	private final Pattern pattern = Pattern.compile("[xX](\\d+)[Yy](\\d+)");


	public ConsolePlayer(String name, Board board) {
		this.name = name;
		this.board = board;
	}

	/**
	 * The method asks a user for a next move
	 * @return coordinates of next user move.
	 */
	@Override
	public Coordinate genNextMove() {
		Coordinate result = null;
		var scanner = new Scanner(System.in);
		System.out.println(name + " enter your turn");
		while (result == null) {
			var answer = scanner.next();
			var matcher = pattern.matcher(answer);
			if (matcher.matches()) {
				int x = Integer.parseInt(matcher.group(1)) - 1;
				int y = Integer.parseInt(matcher.group(2)) - 1;
				result = new Coordinate(x, y);
				if (board.isWrong(result)) {
					result = null;
					System.out.println("Pls. enter correct coordinate. \n\r"
							+ "Your data is out of range or already occupied");
				}
			} else {
				System.out.println("Pls. enter correct data, like: x2y3 \n\r"
						+ " valid numbers from 1 to " + board.getSize());
			}
		}
		return result;
	}

	/**
	 * Method parses message.
	 * @param msg input message.
	 * @return coordinate created from msg.
	 */
	private Coordinate createCoordinate(String msg) {
		Coordinate result = null;
		var ars = msg.toCharArray();
		var size = board.getSize();
		Pattern pattern1 = Pattern.compile("[xX](\\d+)[Yy](\\d+)");
		var matcher = pattern1.matcher(msg);
		if (matcher.matches()) {
			int x = Integer.parseInt(matcher.group(1)) - 1;
			int y = Integer.parseInt(matcher.group(2)) - 1;
			if (x < size && y < size && x >= 0 && y >= 0) {
				result = new Coordinate(x, y);
			}
			System.out.println(matcher.group(2));
		}
		return result;
	}
}
