package ru.job4j.chat;

import java.util.List;

class Arguments {
	private String[] args;
	/**
	 * Keys.
	 */
	private static final String DIRECTORY = "-d";
	private static final String OUTPUT = "-o";
	private static final String COUNT = "-c";
	private static final String MODE = "-m";
	private static final List<String> RANDOM_MODE = List.of("r", "random");
	private static final List<String> SMART_MODE = List.of("s", "smart");
	/**
	 * default sequel length
	 */
	private int count = 3;
	/**
	 * default work mode
	 */
	private String mode = "smart";

	public Arguments(String[] args) {
		this.args = args;
	}


	public String directory() {
		return this.argValue(DIRECTORY);
	}

	public String output() {
		return this.argValue(OUTPUT);
	}

	public String mode() {
		var currArg = argValue(MODE);
		if (!currArg.isEmpty()) {
			if (RANDOM_MODE.contains(currArg)) {
				this.mode = "random";
			} else if (SMART_MODE.contains(currArg)) {
				this.mode = "smart";
			} else {
				this.mode = "invalid";
			}
		}
		return this.mode;
	}

	public int count() {
		var arg = argValue(COUNT);
		if (!arg.isEmpty()) {
			boolean isNumeric = arg.chars().allMatch(Character::isDigit);
			if (isNumeric) {
				this.count = Integer.parseInt(arg);
			} else {
				this.count = -1;
			}
		}
		return this.count;
	}

	private String argValue(String command) {
		String result = "";
		for (int i = 0; i < this.args.length - 1; i++) {
			if (command.equals(args[i])) {
				result = args[i + 1];
				break;
			}
		}
		return result;
	}
}