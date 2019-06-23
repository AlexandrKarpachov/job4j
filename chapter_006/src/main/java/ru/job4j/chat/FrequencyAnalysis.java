package ru.job4j.chat;

import java.util.*;

public  class FrequencyAnalysis {
	/**
	 * Returns Map with all the words in text and their most frequent sequel
	 * @param text input text.
	 * @return Map where key is word, value is sequel.
	 */
	public static Map<String, String> mostFrequentSequel(String text) {
		var result = new HashMap<String, String>();
		var sequels = nextWords(SentencesParser.parseSentences(text));
		for (String key : sequels.keySet()) {
			sequels.get(key).sort(Comparator.naturalOrder());
			var mostFrequencyValue = sequels.get(key).get(0);
			var max = 0;
			var count = 0;
			for (int i = 0; i < sequels.get(key).size() - 1; i++) {
				var currentWord = sequels.get(key).get(i);
				var nextWord = sequels.get(key).get(i + 1);
				if (currentWord.equals(nextWord)) {
					count++;
					if (!(i == sequels.get(key).size() - 2)) {
						continue;
					}
				}
				if (max < count) {
					mostFrequencyValue = sequels.get(key).get(i);
					max = count;
				}
				count = 0;
			}
			result.put(key, mostFrequencyValue);
		}
		return result;
	}

	/**
	 * returns map with all the words in text like keys and lists with all
	 * the possible sequels like a value.
	 */
	private static Map<String, List<String>> nextWords(List<List<String>> sentences) {
		var result = new HashMap<String, List<String>>();
		for (List<String> sentence : sentences) {
			for (int i = 0; i < sentence.size() - 1; i++) {
				result.putIfAbsent(sentence.get(i), new ArrayList<>());
				result.get(sentence.get(i)).add(sentence.get(i + 1));
				if (i < sentence.size() - 2) {
					var key = sentence.get(i) + " " + sentence.get(i + 1);
					result.putIfAbsent(key, new ArrayList<>());
					result.get(key).add(sentence.get(i + 2));
				}
			}
		}
		return result;
	}
}
