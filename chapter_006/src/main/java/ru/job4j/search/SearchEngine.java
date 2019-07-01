package ru.job4j.search;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchEngine {
	/**
	 * variable for storing input arguments.
	 */
	private Arguments args;
	/**
	 * search pattern, or full name depend on mode.
	 */
	private String searchPattern;
	/**
	 * map with methods for comparing
	 */
	private Map<String, Predicate<String>> dispatch = new HashMap<>();

	SearchEngine(Arguments arguments) {
		this.args = arguments;
	}

	public static void main(String[] args) {
		var arguments = new Arguments(args);
		var checker = new ArgChecker();
		var check = checker.check(arguments);
		var searchEngine = new SearchEngine(arguments).init();
		if (!check) {
			System.out.println(checker.getLog());
			checker.deleteLog();
		} else {
			searchEngine.search();
		}
	}

	/**
	 * Method runs files search
	 */
	public void search() {
		var foundFiles = this.seekBy(this.dispatch.get(args.mode()));
		this.writeResult(foundFiles);
		System.out.println("search completed");
	}

	/**
	 * Applies input comparing method to each file name in
	 * {@link Arguments#directory()} directory.
	 */
	private List<File> seekBy(Predicate<String> pred) {
		var rootDir = new File(args.directory());
		var result = new ArrayList<File>();
		Queue<File> directories = new LinkedList<>();
		if (rootDir.isDirectory()) {
			directories.add(rootDir);
		}
		while (!directories.isEmpty()) {
			var file = directories.poll();
			var fileList = file.listFiles();
			if (fileList != null) {
				for (File f : fileList) {
					var fileName = getFileName(f);
					if (f.isDirectory()) {
						directories.add(f);
					} else if (pred.test(fileName)) {
						result.add(f);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Method for class values initialization.
	 *
	 * @return class instance which ready to work.
	 */
	public SearchEngine init() {
		if (args.mode().equals("-m")) {
			this.searchPattern = this.maskToRegex(args.name());
		} else {
			this.searchPattern = args.name();
		}
		this.load(Arguments.FULL_MODE, this::fullName);
		this.load(Arguments.REGEX_MODE, this::regex);
		this.load(Arguments.MASK_MODE, this::regex);
		return this;
	}

	private void load(String mode, Predicate<String> predicate) {
		this.dispatch.put(mode, predicate);
	}

	/**
	 * Method for comparing by full regex.
	 */
	private boolean regex(String name) {
		var pattern = Pattern.compile(this.searchPattern);
		return pattern.matcher(name).find();
	}

	/**
	 * Method for comparing by full name
	 */
	private boolean fullName(String name) {
		return name.equals(this.searchPattern);
	}

	/**
	 * Converts mask expression to regex.
	 */
	private String maskToRegex(String mask) {
		var regex = mask.replaceAll("\\.", "\\\\.");
		regex = regex.replaceAll("\\?", ".");
		return regex.replaceAll("\\*", ".+?");
	}

	/**
	 * @return file name without path to it.
	 */
	private String getFileName(File file) {
		var fullName = file.getName();
		int lastSeparator = fullName.lastIndexOf(File.separator);
		if (lastSeparator != -1) {
			fullName = fullName.substring(lastSeparator + 1);
		}
		return fullName;
	}

	/**
	 * writes input file list to {@link Arguments#output()} file.
	 */
	private void writeResult(List<File> foundFiles) {
		try (var out = new PrintWriter(new FileWriter(args.output()))) {
			foundFiles.forEach(f -> out.println(f.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
