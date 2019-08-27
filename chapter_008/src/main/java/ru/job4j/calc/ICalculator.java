package ru.job4j.calc;

import ru.job4j.calculate.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


public class ICalculator {

	/**
	 * An instance of calculator class which store methods for different calculations.
	 */
	private final static Calculator OPERATIONS = new Calculator();

	/**
	 * list of available calculation methods.
	 */
	private final static Map<String, BiFunction<Double, Double, Double>> METHODS = new HashMap<>() {
		{
			put("+", OPERATIONS::add);
			put("-", OPERATIONS::subtract);
			put("*", OPERATIONS::multiply);
			put("/", OPERATIONS::divide);
		}
	};

	/**
	 * The method calculates an input expression.
	 * @param operation which will be performed.
	 * @param a first argument.
	 * @param b second argument.
	 * @return result of calculation.
	 */
	public double calculate(String operation, double a, double b) {
		return METHODS.get(operation).apply(a, b);
	}

	/**
	 * The method returns all available operations which presents as symbols
	 */
	public List<String> getOperationList() {
		return new ArrayList<>(METHODS.keySet());
	}

}
