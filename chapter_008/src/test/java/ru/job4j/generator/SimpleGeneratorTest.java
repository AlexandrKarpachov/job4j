package ru.job4j.generator;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {

	@Test
	public void whenPutTemplateAndValuesThenOk() {
		var generator = new SimpleGenerator();
		var values = new HashMap<String, String>();
		String pattern = "I am a ${name}, Who are ${subject}?";
		values.put("name", "Alex");
		values.put("subject", "you");
		var expected = "I am a Alex, Who are you?";

		var actual = generator.generate(pattern, values);

		assertThat(actual, is(expected));
	}

	@Test
	public void whenPutTemplateWithRepeatedKeysThenOk() {
		var generator = new SimpleGenerator();
		var values = new HashMap<String, String>();
		String pattern = "I am a ${name}, Who are ${subject}? ${subject} are here?";
		values.put("name", "Alex");
		values.put("subject", "you");
		var expected = "I am a Alex, Who are you? you are here?";

		var actual = generator.generate(pattern, values);

		assertThat(actual, is(expected));
	}

	@Test (expected = UnusedKeyException.class)
	public void whenPutExcessKeyThenException() {
		var generator = new SimpleGenerator();
		String pattern = "I am a ${name}, Who are ${subject}?";
		var values = new HashMap<String, String>();
		values.put("name", "Alex");
		values.put("subject", "you");
		values.put("extra", "value");

		var actual = generator.generate(pattern, values);
	}

	@Test (expected = NotFoundKeyException.class)
	public void whenPutKeySetWithMissingValueThenException() {
		var generator = new SimpleGenerator();
		String pattern = "I am a ${name}, Who are ${subject}?";
		var values = new HashMap<String, String>();
		values.put("name", "Alex");

		var actual = generator.generate(pattern, values);
	}
}