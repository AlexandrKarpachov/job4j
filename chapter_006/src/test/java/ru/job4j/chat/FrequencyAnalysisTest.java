package ru.job4j.chat;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FrequencyAnalysisTest {
	@Test
	public void textWithOneSentenceWithMultipleWordsThanReturnCorrectResult() {
		var text = "a b a b a b a c a c a c a e a e";
		var expected = new HashMap<String, String>();
		expected.put("a", "b");
		expected.put("b", "a");
		expected.put("c", "a");
		expected.put("e", "a");
		expected.put("a b", "a");
		expected.put("b a", "b");
		expected.put("a c", "a");
		expected.put("c a", "c");
		expected.put("a e", "a");
		expected.put("e a", "e");

		var actual = FrequencyAnalysis.mostFrequentSequel(text);

		assertThat(actual, is(expected));
	}

}