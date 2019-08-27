package ru.job4j.calc;

import java.util.List;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 26.08.2019
 *
 * Before working with class is necessary to {@link #init()} it.
 */
public class ConsoleApp {

	private ICalculator calculator;
	/**
	 * this variable answers for data input
	 */
	private final Input input;
	/**
	 * the variable store first value or result of previous calculation.
	 */
	private double previous = 0;
	/**
	 * the variable store second value
	 */
	private double second;
	/**
	 * the variable store operator.
	 */
	private String operation = "";
	/**
	 * It's a list of all available operators. for initiation this variable use {@link #init()} method.
	 */
	private List<String> operations;

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
	private final static String HELLO_MASSAGE = "This programm is a console calculator"
			+ System.lineSeparator()
			+ "available operations: "
			+ new ICalculator().getOperationList().toString()
			+ System.lineSeparator()
			+ "For Exit enter one of these commands: "
			+ EXIT_COMMANDS.toString()
			+ System.lineSeparator();

	public ConsoleApp(Input input) {
		this.input = input;
	}

	/**
	 * the method initiates some variables for correct work.
	 * @return an instance of this class.
	 */
	public ConsoleApp init() {
		this.calculator = new ICalculator();
		this.operations = this.calculator.getOperationList();
		return this;
	}

	/**
	 * The main method which started the application.
	 */
	public static void main(String[] args) {
		var app = new ConsoleApp(new ConsoleInput()).init();
		app.run();
	}

	public void run() {
		System.out.println(HELLO_MASSAGE);
		var wasExit = false;
		while (!wasExit) {
			if (!this.getFirstNum()) {
				wasExit = true;
			} else if (this.operation.isEmpty() && !this.getOperation()) {
				wasExit = true;
			} else if (this.calculator.shortOperations().contains(this.operation)) {
				var result = this.calculator.calculate(this.operation, this.previous);
				this.printResult(result);
			} else if (!this.getSecondNum()) {
				wasExit = true;
			} else {
				var result = this.calculator.calculate(this.operation, this.previous, this.second);
				this.printResult(result);
			}
		}
	}

	/**
	 * The method prints result on console, clears operator, and sets previous value
	 */
	private void printResult(double result) {
		System.out.println("result is " + result);
		this.previous = result;
		this.operation = "";
	}

	/**
	 * The method asks a user for first value or operator.
	 * * If the user enters an operator, the value of the previous calculation will be assigned to the first argument.
	 * (If this is the first calculation firs value will be assigned by zero.)
	 * @return true if a user enters a correct value or false if He entered one of the exit keywords.
	 */
	private boolean getFirstNum() {
		var result = true;
		var resultObtained = false;
		var answer = this.input.ask("Enter first  or operator for working with previous value");
		while (!resultObtained) {
			if (EXIT_COMMANDS.contains(answer.toLowerCase())) {
				result = false;
				break;
			}
			if (this.operations.contains(answer)) {
				this.operation = answer;
				resultObtained = true;
			} else {
				try {
					this.previous = Double.parseDouble(answer);
					resultObtained = true;
				} catch (Exception e) {
					answer = this.input.ask("Please, enter correct data");
				}
			}
		}
		return result;
	}

	/**
	 * The method asks a user for operator.
	 * @return true if a user enters a correct value or false if He entered one of the exit keywords.
	 */
	private boolean getOperation() {
		var result = true;
		var resultObtained = false;
		var answer = this.input.ask("Enter operator");
		while (!resultObtained) {
			if (EXIT_COMMANDS.contains(answer.toLowerCase())) {
				result = false;
				break;
			}
			if (this.operations.contains(answer)) {
				this.operation = answer;
				resultObtained = true;
			} else {
				answer = this.input.ask("Please, enter correct data");
			}
		}
		return result;
	}

	/**
	 * The method asks a user for a numeric.
	 * @return true if a user enters a correct value or false if He entered one of the exit keywords.
	 */
	private boolean getSecondNum() {
		var result = true;
		var resultObtained = false;
		var answer = this.input.ask("Enter second num");
		while (!resultObtained) {
			if (EXIT_COMMANDS.contains(answer.toLowerCase())) {
				result = false;
				break;
			}
			try {
				this.second = Double.parseDouble(answer);
				resultObtained = true;
			} catch (Exception e) {
				answer = this.input.ask("Please, enter correct data");
			}
		}
		return result;
	}

}
