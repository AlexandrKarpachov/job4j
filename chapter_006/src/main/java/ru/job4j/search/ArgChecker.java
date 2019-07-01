package ru.job4j.search;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Class ArgChecker. Validates {@link Arguments} values.
 *
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 30.06.2019
 */
class ArgChecker {

	/**
	 * main method which validates all arguments
	 * @param args input Arguments class instance
	 * @return true if all arguments are correct,
	 * otherwise - false.
	 */
	boolean check(Arguments args) {
		var dir = this.rootCheck(args.directory());
		var name = this.nameCheck(args.name());
		var mode = this.modeCheck(args.mode());
		var out = this.outCheck(args.output());
		return dir && name && mode && out;
	}

	/**
	 * Checks that {@link Arguments#mode()} returns correct value
	 * If argument are not valid information about it adds to issueLog.log.
	 */
	private boolean modeCheck(String mode) {
		var result = Arguments.MODS.contains(mode);
		if (!result) {
			this.log("Please choose searching mode" + System.lineSeparator());
		}
		return result;
	}

	/**
	 * Checks that {@link Arguments#name()} returns not empty field.
	 * It may occur if user forgot to enter the key "-n" or name of looking file.
	 * If argument are not valid information about it adds to issueLog.log.
	 */
	private boolean nameCheck(String name) {
		var result = !name.isEmpty();
		if (!result) {
			this.log("Please, enter file name, like: -n searchingFile.ext"
					+ System.lineSeparator());
		}
		return result;
	}

	/**
	 * Checks that {@link Arguments#output()} returns a valid data.
	 * @return <tt>true</tt> if program can create output file, or overwrite if it already
	 * exists. Otherwise returns <tt>false</tt>, and adds to issueLog.log information about it.
	 */

	private boolean outCheck(String name) {
		var result = false;
		var outFile = new File(name);
		var outExists = outFile.exists();
		if (outExists) {
			result = outFile.canWrite();
		} else {
			try {
				result = outFile.createNewFile();
			} catch (IOException e) {
				this.log(e.getMessage());
			}
			//noinspection ResultOfMethodCallIgnored
			outFile.delete();
		}
		if (!result) {
			this.log(String.format("Can't create or overwrite %s output file. (Access denied)%s",
					name, System.lineSeparator()));
		}
		return result;
	}

	/**
	 * Checks that {@link Arguments#directory()} returns a valid data.
	 * @return <tt>true</tt> if directory with such name exists.
	 * Otherwise returns <tt>false</tt>, and adds to issueLog.log information about it.
	 */
	private boolean rootCheck(String root) {
		var result = new File(root).isDirectory();
		if (!result) {
			this.log(String.format("Can't find \"%s\" directory%s",
					root, System.lineSeparator()));
		}
		return result;
	}

	/**
	 * Creates or appends to already exists issueLog.log file input string.
	 */
	private void log(String issue) {
		var temp = System.getProperty("java.io.tmpdir");
		var fileName = temp + File.pathSeparator + "issueLog.log";
		try (var writer = new PrintWriter(new FileWriter(fileName, true))) {
			writer.println(issue);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return information about issues in args + help information.
	 */

	public String getLog() {
		var result = "";
		var temp = System.getProperty("java.io.tmpdir");
		var logPath = temp + File.pathSeparator + "issueLog.log";
		var helpPath = Objects.requireNonNull(getClass().getClassLoader().getResource("help.txt")).getFile();
		try {
			result += FileUtils.readFileToString(new File(logPath), StandardCharsets.UTF_8);
			result += FileUtils.readFileToString(new File(helpPath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Delete issueLog.log file.
	 */
	public void deleteLog() {
		var temp = System.getProperty("java.io.tmpdir");
		var logPath = temp + File.pathSeparator + "issueLog.log";
		var log = new File(logPath);
		//noinspection ResultOfMethodCallIgnored
		log.delete();
	}

}
