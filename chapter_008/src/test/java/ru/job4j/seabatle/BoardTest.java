package ru.job4j.seabatle;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BoardTest {
	private static final int SIZE = 10;
	private Board board = mock(Board.class);
	private Ship ship = mock(Ship.class);
	private Coordinate coord = new Coordinate(1, 1);


	@Test
	public void whenAddBombardCellThenIsBombarded() {
		when(this.board.isBombarded(this.coord)).thenReturn(true);

		this.board.bombard(this.coord);
		var result = this.board.isBombarded(this.coord);

		assert (result);
	}

	@Test
	public void whenAddShipThenReturnItByCoordinate() {
		when(this.board.getShip(this.coord)).thenReturn(this.ship);

		this.board.addShip(ship);
		var result = this.board.getShip(this.coord);

		assertThat(result, is(this.ship));
	}

	/*
	@Test
	public void whenAttemptToGetNonExistingShipThenFalse(){
		Board board = new Board(SIZE);

		var result = board.getShip(new Coordinate(2, 2));

		assert (!result);
	}

	@Test
	public void whenAddShipWithOutOfRangeCoordThenFalse() {
		Board board = new BoardRealization(size);
		var coordinates = List.of(
				new Coordinate(SIZE, SIZE),
				new Coordinate(SIZE + 1, SIZE),
				new Coordinate(SIZE + 2, SIZE )
		);
		var result = board.addShip(new ShipRealization(coordinates));

		assert (!result);
	}

	@Test
	public void whenAddShipWithOutOfRangeCoordThenFalse() {
		Board board = new Board(size);
		var coordinates = List.of(
				new Coordinate(2, 1),
				new Coordinate(2, 2),
				new Coordinate(2, 3 )
		);
		board.addShip(new Ship(coordinates));

		var result = board.addShip(new ShipRealization(coordinates));

		assert (!result);
	}

	@Test
	public void whenAddShipWithToNearCoordThenFalse() {
		Board board = new Board(size);
		var coordinates = List.of(
				new Coordinate(2, 1),
				new Coordinate(2, 2)
		);
		board.addShip(new ShipRealization(coordinates));
		var resultList = new ArrayList<Boolean>();
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(0, 0)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(0, 1)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(0, 2)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(0, 3)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(1, 1)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(1, 3)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(1, 2)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(2, 0)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(2, 3)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(1, 3)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(3, 0)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(3, 1)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(3, 2)))));
		resultList.add(board.addShip(new ShipRealization(List.of(new Coordinate(3, 3)))));

		var result = resultList.stream().noneMatch(x -> x);

		assert (result);
	}

	@Test
	public void whenHitInShipThenBombardReturnTrue() {
		Board board = new Board(size);
		var coordinates = List.of(
				new Coordinate(2, 1),
				new Coordinate(2, 2),
				new Coordinate(2, 3 )
		);
		board.addShip(new Ship(coordinates));

		var result = board.bombard(new Coordinate(2, 2));

		assert (result);
	}
	 */
}
