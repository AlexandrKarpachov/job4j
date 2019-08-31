package ru.job4j.calc;

import ru.job4j.calculate.Calculator;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 26.08.2019
 */
public class ConsoleApp {

	private Operations operations;
	/**
	 * this variable answers for data input
	 */
	private final Input input;
	/**
	 * store result of previous calculation or zero if it is first.
	 */
	private double previous = 0;

	/**
	 * the variable store commands for using previous result.
	 */
	private static final List<String> PREVIOUS = List.of("p");
	/**
	 * It's a list of all commands which you can use for close the program.
	 */
	private final static List<String> EXIT_COMMANDS = List.of(
			"quit",
			"exit",
			"q"
	);

	/**
	 * The first welcome message which will be printed when program start.
	 */
	private String welcomeMsg() {
		return "This program is a console operations"
				+ System.lineSeparator()
				+ "available ListOfMarks: "
				+ this.operations.getOperationList().toString()
				+ System.lineSeparator()
				+ "For Exit enter one of these commands: "
				+ EXIT_COMMANDS.toString()
				+ System.lineSeparator();
	}

	public ConsoleApp(Operations operations, Input input) {
		this.operations = operations.init();
		this.input = input;
	}

	public static void main(String[] args) {
		var app = new ConsoleApp(new EngineerOperations(new Calculator()), new ConsoleInput());
		app.run();
	}

	/**
	 * The method starts the application.
	 */
	public void run() {
		System.out.println(this.welcomeMsg());
		var isProceed = true;
		while (isProceed) {
			List<Double> args = new ArrayList<>();
			String operation = this.getOperation();
			var argsAmount = this.operations.getNumberOfArgs(operation);
			for (int i = 0; i < argsAmount; i++) {
				String msg = String.format("Enter %d value, for using result of previous calculations "
						+ "enter one of these commands %s", i + 1, PREVIOUS);
				args.add(this.getNumber(msg));
			}
			var rst = operations.calculate(operation, args);
			this.previous = rst;
			System.out.println("result is " + rst);
			var proceed = this.input.ask("Do you want continue y/n");
			if (!proceed.equalsIgnoreCase("y")
					&& !proceed.equalsIgnoreCase("yes")) {
				isProceed = false;
			}
		}
	}

	/**
	 * The method asks a user for operator.
	 * @return operator.
	 */
	private String getOperation() {
		var result = "";
		var resultObtained = false;
		var answer = this.input.ask("Enter operator").toLowerCase();
		while (!resultObtained) {
			if (EXIT_COMMANDS.contains(answer)) {
				System.exit(0);
			}
			if (this.operations.checkOperation(answer)) {
				result = answer;
				resultObtained = true;
			} else {
				answer = this.input.ask("Please, enter correct data").toLowerCase();
			}
		}
		return result;
	}

	/**
	 * The method asks a user for a numeric.
	 * @return number.
	 */
	private double getNumber(String msg) {
		double result = Double.NaN;
		var resultObtained = false;
		var answer = this.input.ask(msg);
		while (!resultObtained) {
			if (EXIT_COMMANDS.contains(answer.toLowerCase())) {
				System.exit(0);
			}
			if (PREVIOUS.contains(answer)) {
				result = previous;
				resultObtained = true;
			} else {
				try {
					result = Double.parseDouble(answer);
					resultObtained = true;
				} catch (Exception e) {
					answer = this.input.ask("Please, enter correct data");
				}
			}
		}
		return result;
	}
}
