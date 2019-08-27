package ru.job4j.calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConsoleAppTest {
	private final PrintStream stdOut = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before
	public void before() {
		System.setOut(new PrintStream(this.out));
	}

	@After
	public void backOutput() {
		System.setOut(stdOut);
	}

	@Test
	public void whenAddTwoValuesThenOk() {
		var input = new ManualInput(List.of("4", "+", "5", "q"));
		var app = new ConsoleApp(input).init();

		app.run();
		var printed = out.toString();
		var actual = this.getResult(printed);

		assertThat(actual, is(9.0));
	}

	@Test
	public void whenUsePreviousCalculatedValueThenOk() {
		var input = new ManualInput(List.of("4", "+", "5", "/", "2", "q"));
		var app = new ConsoleApp(input).init();

		app.run();
		var printed = out.toString();
		var actual = this.getResult(printed);

		assertThat(actual, is(4.5));
	}

	@Test
	public void whenMultiplyTwoValuesThenOk() {
		var input = new ManualInput(List.of("4", "*", "5", "q"));
		var app = new ConsoleApp(input).init();

		app.run();
		var printed = out.toString();
		var actual = this.getResult(printed);

		assertThat(actual, is(20.0));
	}

	@Test
	public void whenSubtractTwoValuesThenOk() {
		var input = new ManualInput(List.of("4", "-", "5", "q"));
		var app = new ConsoleApp(input).init();

		app.run();
		var printed = out.toString();
		var actual = this.getResult(printed);

		assertThat(actual, is(-1.0));
	}

	private double getResult(String output) {
		var index = output.lastIndexOf("result is");
		var result = output.substring(index + 10);
		Pattern p = Pattern.compile("-?\\d+\\.\\d+");
		Matcher m = p.matcher(result);
		//noinspection ResultOfMethodCallIgnored
		m.find();
		return Double.parseDouble(m.group());
	}

}

