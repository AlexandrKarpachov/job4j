package ru.job4j.bot;

import com.google.common.base.Charsets;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for a simple Chat bot
 * before using this class you must have files with answers and patterns
 * and then run  <tt>init()</tt>
 *
 * @author Aleksander Karpachov
 * @version $Id$
 * @since 27.06.2019
 */
public class Bot {
	private List<String> commonPhrases = new ArrayList<>();
	private List<String> elusiveAnswers = new ArrayList<>();
	private Map<String, String> patterns = new HashMap<>();
	private Map<String, String> answersByPatterns = new HashMap<>();

	/**
	 * Method loads answers and patterns from files.
	 *
	 * @return an instance of this class with loaded fields
	 */
	public Bot init() {
		try (var readCom = this.getFileStream("commonPhrases.txt");
		     var readEl = this.getFileStream("elusiveAnswers.txt");
		     var readPat = this.getFileStream("patterns.txt");
		     var readAns = this.getFileStream("answers.txt")) {
			this.commonPhrases = readCom.lines().collect(Collectors.toList());
			this.elusiveAnswers = readEl.lines().collect(Collectors.toList());
			this.patterns = toMap(readPat.lines());
			this.answersByPatterns = toMap(readAns.lines());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	private BufferedReader getFileStream(String name) throws IOException {
		//noinspection ConstantConditions
		var file = getClass().getClassLoader().getResource(name).getFile();
		return new BufferedReader(new FileReader(file, Charsets.UTF_8));
	}

	private Map<String, String> toMap(Stream<String> stream) {
		return stream
				.filter(line -> !line.startsWith("#") && !line.isBlank())
				.map(line -> line.split("; "))
				.collect(Collectors.toMap(e -> e[0], e -> e[1]));
	}

	/**
	 * Method for answers generating. If method recognizes
	 * in input massage pattern it return "smart" answer depended on ask
	 * Otherwise, a random response from the file will be selected.
	 * @param msg input message.
	 * @return bot answer.
	 */
	public String sayInReturn(String msg) {
		String say;
		var smart = this.smartAnswer(msg);
		if (!smart.equals("")) {
			say = smart;
		} else {
			say = this.randomAnswer(msg);
		}
		return say;
	}

	private String randomAnswer(String msg) {
		Random random = new Random();
		return msg.trim().endsWith("?")
				? this.elusiveAnswers.get(random.nextInt(this.elusiveAnswers.size()))
				: this.commonPhrases.get(random.nextInt(this.commonPhrases.size()));
	}

	private String smartAnswer(String msg) {
		var say = "";
		String message =
				String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
		for (Map.Entry<String, String> o : this.patterns.entrySet()) {
			Pattern pattern = Pattern.compile(o.getKey());
			if (pattern.matcher(message).find()) {
				if (o.getValue().equals("whattime")) {
					say = new Date().toString();
					break;
				} else {
					say = answersByPatterns.get(o.getValue());
					break;
				}
			}
		}
		return say;
	}
}
