package ru.job4j.calc;

import ru.job4j.calculate.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Operations {

	/**
	 * An instance of calculator class which store methods for different calculations.
	 */

	private Calculator calculator;

	private Map<String, Operation> operationsList = new HashMap<>();

	public Operations init() {
		var add = new Add();
		var subtract = new Subtract();
		var multiply = new Multiply();
		var divide = new Divide();
		operationsList.put(add.getMark(), add);
		operationsList.put(subtract.getMark(), subtract);
		operationsList.put(multiply.getMark(), multiply);
		operationsList.put(divide.getMark(), divide);
		return this;
	}

	public Operations(Calculator calculator) {
		this.calculator = calculator;
	}

	public List<String> getOperationList() {
		return new ArrayList<>(operationsList.keySet());
	}

	public boolean checkOperation(String mark) {
		return operationsList.containsKey(mark);
	}

	public int getNumberOfArgs(String mark) {
		return this.operationsList.get(mark).getNumberOfArgs();
	}

	public double calculate(String operation, List<Double> args) {
		return this.operationsList.get(operation).calculate(args);
	}

	class Add extends Operation {

		public Add() {
			super(2, "+");
		}

		@Override
		public double calculate(List<Double> args) {
			return calculator.add(args.get(0), args.get(1));
		}
	}

	class Subtract extends Operation {

		public Subtract() {
			super(2, "-");
		}

		@Override
		public double calculate(List<Double> args) {
			return calculator.subtract(args.get(0), args.get(1));
		}
	}

	class Multiply extends Operation {

		public Multiply() {
			super(2, "*");
		}

		@Override
		public double calculate(List<Double> args) {
			return calculator.multiply(args.get(0), args.get(1));
		}
	}

	class Divide extends Operation {

		public Divide() {
			super(2, "/");
		}

		@Override
		public double calculate(List<Double> args) {
			return calculator.divide(args.get(0), args.get(1));
		}
	}
}
