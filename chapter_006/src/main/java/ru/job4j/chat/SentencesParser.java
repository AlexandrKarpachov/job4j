package ru.job4j.chat;

import java.util.ArrayList;
import java.util.List;

public class SentencesParser {

	/**
	 * Creates List with sentences. Each nested list store set of words from a sentence.
	 */
	public static List<List<String>> parseSentences(String text) {
		var sentencesList = new ArrayList<List<String>>();
		String[] sentencesArray = text.split("[.!?;:()]");
		for (String sentence : sentencesArray) {
			if (!sentence.isBlank()) {
				var list = sentenceToListWords(sentence);
				if (!list.isEmpty()) {
					sentencesList.add(list);
				}
			}
		}
		return sentencesList;
	}

	/**
	 * Divide sentence into {@code List} words
	 * @param sentence input sentence
	 * @return List with words.
	 */
	private static List<String> sentenceToListWords(String sentence) {
		var sentencesList = new ArrayList<String>();
		var word = new StringBuilder();
		for (int i = 0; i < sentence.length(); i++)	{
			var character = sentence.charAt(i);
			if (Character.isLetter(character) || character == '\'') {
				word.append(character);
				if (i == sentence.length() - 1 && word.length() != 0) {
					sentencesList.add(word.toString().toLowerCase());
				}
			} else if (word.length() != 0) {
				sentencesList.add(word.toString().toLowerCase());
				word = new StringBuilder();
			}
		}
		return sentencesList;
	}
}
