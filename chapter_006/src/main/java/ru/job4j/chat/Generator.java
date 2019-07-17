package ru.job4j.chat;

import java.util.Date;
import java.util.Random;

class Generator {
	/**
	 * output sentence length.
	 */
	private int wordsCount;

	private String text;

	public Generator(int count, String text) {
		this.wordsCount = count;
		this.text = text;
	}

	/**
	 * Generates sentence by bigram and threegram methods
	 * @param start source for bigram and trigram.
	 */
	public String textGenerate(String start) {
		var sequels = FrequencyAnalysis.mostFrequentSequel(this.text);

		for (int i = 0; i < this.wordsCount; i++) {
			var phraseBegin = start.split(" ");
			var length = phraseBegin.length;
			if (length >= 2 && sequels.containsKey(phraseBegin[length - 2] + " " + phraseBegin[length - 1])) {
				var key = phraseBegin[length - 2] + " " + phraseBegin[length - 1];
				//noinspection all
				start += (" ".concat(sequels.get(key)));
			} else if (sequels.containsKey(phraseBegin[length - 1])) {
				var key = phraseBegin[length - 1];
				start += (" ".concat(sequels.get(key)));
			} else {
				break;
			}
		}
		return start;
	}

	/**
	 * Generates sentence from random words.
	 */
	public String textGenerate() {
		String[] wordsArray = this.text.split("[^a-zA-Z]+");
		var result = new StringBuilder();
		for (int i = 0; i < wordsCount; i++) {
			int index = new Random().nextInt(wordsArray.length);
			result.append(wordsArray[index].toLowerCase().concat(" "));
		}
		return result.deleteCharAt(result.length() - 1).toString();
	}

	public  String time() {
		return new Date().toString();
	}

	public  String hello() {
		return "Hello, my dear friend";
	}

	public  String first() {
		var wordsArray = this.text.split("[^a-zA-Z]+");
		var result = "";
		var length = this.wordsCount < wordsArray.length
				? this.wordsCount : wordsArray.length;
		for (int i = 0; i < length; i++) {
			result = String.join(" ", wordsArray[i]);
		}
		return result;
	}
}
