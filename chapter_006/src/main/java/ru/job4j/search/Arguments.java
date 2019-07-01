package ru.job4j.search;

import java.util.List;

/**
 * Class for storing input arguments from {@link SearchEngine}
 *
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 29.06.2019
 */
class Arguments {
	/**
	 * Keys.
	 */
	private static final String DIRECTORY = "-d";
	private static final String OUTPUT = "-o";
	private static final String NAME = "-n";
	static final String FULL_MODE = "-f";
	static final String REGEX_MODE = "-r";
	static final String MASK_MODE = "-m";
	static final List<String> MODS
			= List.of(MASK_MODE, REGEX_MODE, FULL_MODE);
	private final String[] args;

	Arguments(String[] args) {
		this.args = args;
	}

	public String directory() {
		return this.argValue(DIRECTORY);
	}

	public String name() {
		return this.argValue(NAME);
	}

	public String output() {
		return this.argValue(OUTPUT);
	}

	public String mode() {
		String result = "";
		for (String arg : this.args) {
			if (MODS.contains(arg)) {
				result = arg;
				break;
			}
		}
		return result;
	}

	private String argValue(String command) {
		String result = "";
		for (int i = 0; i < this.args.length - 1; i++) {
			if (command.equals(this.args[i])) {
				result = args[i + 1];
				break;
			}
		}
		return result;
	}
}
