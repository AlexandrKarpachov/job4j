package ru.job4j.chat;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SentencesParserTest {
	@Test
	public void textWithOneSentenceWith4WordsThenReturnCorrectResult() {
		var text = "1. THE BOY WHO LIVED";
		var expected = new ArrayList<List<String>>();
		expected.add(List.of("the", "boy", "who", "lived"));

		var actual = SentencesParser.parseSentences(text);

		assertThat(expected, is(actual));
	}

	@Test
	public void wordContainingApostropheThenReturnCorrectResult() {
		var text = "it's";
		var expected = new ArrayList<List<String>>();
		expected.add(List.of("it's"));

		var actual = SentencesParser.parseSentences(text);

		assertThat(expected, is(actual));
	}

	@Test
	public void sentenceWithManyDelimitersThenCorrectlyParse() {
		var text = "a.b!c?d:e;f(g)h;i";
		var expected = new ArrayList<List<String>>();
		expected.add(List.of("a"));
		expected.add(List.of("b"));
		expected.add(List.of("c"));
		expected.add(List.of("d"));
		expected.add(List.of("e"));
		expected.add(List.of("f"));
		expected.add(List.of("g"));
		expected.add(List.of("h"));
		expected.add(List.of("i"));


		var actual = SentencesParser.parseSentences(text);

		assertThat(expected, is(actual));
	}

	@Test
	public void sentenceWithSpecialCharactersThenCorrectlyParse() {
		var originalText = "b;\tc;\rd;\ne;\r\nf;\r\n\r\ng";
		var expected = new ArrayList<List<String>>();
		expected.add(List.of("b"));
		expected.add(List.of("c"));
		expected.add(List.of("d"));
		expected.add(List.of("e"));
		expected.add(List.of("f"));
		expected.add(List.of("g"));

		var actual = SentencesParser.parseSentences(originalText);

		assertThat(expected, is(actual));
	}
}

