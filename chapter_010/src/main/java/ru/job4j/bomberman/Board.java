package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 03.10.2019
 */
public class Board {
	private final int size;
	private final ReentrantLock[][] board;

	public Board(int size) {
		this.size = size;
		board = new ReentrantLock[size][size];
	}

	public Board init() {
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				board[i][k] = new ReentrantLock();
			}
		}
		return this;
	}

	public boolean move(Cell source, Cell dist)  {
		boolean result = false;
		try {
			result = board[dist.getX()][dist.getY()].tryLock(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (result) {
			board[source.getX()][source.getY()].unlock();
		}
		return result;
	}
}
