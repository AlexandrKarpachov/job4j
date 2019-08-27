package ru.job4j.calc;

import ru.job4j.calculate.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;


public class ICalculator {

	/**
	 * An instance of calculator class which store methods for different calculations.
	 */
	private final static Calculator OPERATIONS = new Calculator();
	private final static EngineerCalculator ENGINEER_OPERATIONS = new EngineerCalculator();
	/**
	 * list of available calculation methods which need two values.
	 */
	private final static Map<String, BiFunction<Double, Double, Double>> METHODS = new HashMap<>() {
		{
			put("+", OPERATIONS::add);
			put("-", OPERATIONS::subtract);
			put("*", OPERATIONS::multiply);
			put("/", OPERATIONS::divide);
			put("pow", ENGINEER_OPERATIONS::pov);
		}
	};
	/**
	 * list of available calculation methods which need only one value.
	 */
	private final static Map<String, Function<Double, Double>> SHORT_METHODS = new HashMap<>() {
		{
			put("cos", ENGINEER_OPERATIONS::cos);
			put("sin", ENGINEER_OPERATIONS::sin);
			put("log", ENGINEER_OPERATIONS::log);
			put("sqrt", ENGINEER_OPERATIONS::sqrt);
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

	public double calculate(String operation, double a) {
		return SHORT_METHODS.get(operation).apply(a);
	}

	/**
	 * The method returns all available operations.
	 */
	public List<String> getOperationList() {
		var result = new ArrayList<>(METHODS.keySet());
		result.addAll(SHORT_METHODS.keySet());
		return result;
	}

	/**
	 * The method returns all available operations that require a single value.
	 */
	public List<String> shortOperations() {
		return new ArrayList<>(SHORT_METHODS.keySet());
	}

}
