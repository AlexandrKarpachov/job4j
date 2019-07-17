package ru.job4j.chat;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Chat {
	private static final List<String> PAUSE_COMMANDS = List.of("стоп", "пауза");
	private static final List<String> STOP_COMMANDS = List.of("закончить", "Астанавись!!!");
	private static final List<String> CONTINUE_COMMANDS = List.of("продолжить", "возобновить");
	private Map<String, Supplier<String>> answers = new HashMap<>();
	private final Generator generator;

	private Chat(Generator generator) {
		this.generator = generator;
	}

	public Chat init() {
		this.answers.put("How time?", this.generator::time);
		this.answers.put("Hello", this.generator::hello);
		this.answers.put("Start text", this.generator::first);
		return this;
	}
	/**
	 * main method
	 */
	public static void main(String[] args) {
		var argument = new Arguments(args);
		int count = argument.count();
		String mode = argument.mode();
		String out = argument.output();
		String text = readFile(argument.directory());
		var chat = new Chat(new Generator(count, text)).init();
		if (count > 0 && !mode.equals("invalid")) {
			if (mode.equals("smart")) {
			 	chat.run(out, true);
			} else {
				chat.run(out, false);
			}
		} else {
			System.out.println("Please enter correct args");
		}
	}

	/**
	 * Reads text from file
	 * @param dir file name.
	 * @return String with all text.
	 */
	private static String readFile(String dir) {
		String result = "";
		File file = new File(dir);
		try {
			result = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return result;
	}

	/**
	 * runs the chat
	 * @param outDir name of file for logging chat
	 * @param isSmart work mode of chat. If <tt>true</tt>
	 *                answers will be more meaningful, otherwise
	 *                they will consists from random words.
	 */
	private void run(String outDir, boolean isSmart) {
		var scanner = new Scanner(System.in);
		System.out.println("Введите предложение");
		String in = scanner.nextLine();
		boolean answer = true;
		try (PrintWriter out = new PrintWriter(new FileOutputStream(outDir))) {
			while (!STOP_COMMANDS.contains(in)) {
				if (PAUSE_COMMANDS.contains(in)) {
					answer = false;
				} else if (CONTINUE_COMMANDS.contains(in)) {
					answer = true;
				}
				out.println(in);
				if (answer && !CONTINUE_COMMANDS.contains(in)) {
					if (isSmart) {
						var ans = generator.textGenerate(in);
						System.out.println(ans);
						out.println(ans);
					} else {
						var ans = answers.getOrDefault(in, this.generator::textGenerate);
						System.out.println(ans);
						out.println(ans);
					}
				}
				in = scanner.nextLine();
			}
			out.println(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
