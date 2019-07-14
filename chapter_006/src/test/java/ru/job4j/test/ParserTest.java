package ru.job4j.test;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParserTest {
	@Test
	public void whenOnlyOpenThenEmpty() {
		this.check("{", List.of(""));
	}

	@Test
	public void whenWrongBracesAndCharThenOnlyChar() {
		this.check("}a{", List.of("a"));
	}

	@Test
	public void whenManyVariantsThenOnlyCorrect() {
		this.check("{}}{}}", List.of("{{}}", "{}{}"));
	}

	@Test
	public void whenManyVariantsWithCharThenOnlyCorrect() {
		this.check("{}x}x}", List.of("{x}x", "{}xx", "{xx}"));
	}

	public void check(String input, List<String> expect) {
		Parser parser = new Parser();
		var actual = parser.parse(input);

		assertThat(actual, is(expect));
	}
}