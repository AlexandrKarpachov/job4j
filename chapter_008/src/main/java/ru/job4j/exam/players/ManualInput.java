package ru.job4j.exam.players;

import ru.job4j.exam.Coordinate;


import java.util.List;

public class ManualInput extends Player {
	List<Coordinate> list;
	private int pointer = 0;

	public ManualInput(List<Coordinate> list) {
		this.list = list;
	}

	@Override
	public Coordinate genNextMove() {
		return list.get(pointer++);
	}
}
