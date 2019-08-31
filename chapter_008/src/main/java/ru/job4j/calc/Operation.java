package ru.job4j.calc;

import java.util.List;

public abstract class Operation {
	private int numberOfArgs;
	private String mark;

	public Operation(int numberOfArgs, String mark) {
		this.numberOfArgs = numberOfArgs;
		this.mark = mark;
	}

	abstract double calculate(List<Double> args);

	int getNumberOfArgs() {
		return numberOfArgs;
	}

	String getMark() {
		return mark;
	}
}
