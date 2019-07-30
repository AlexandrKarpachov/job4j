package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainTest {

	File target;
	File dest;
	File scheme;


	@Before
	public void init() throws IOException {
		this.target = File.createTempFile("target", ".xml");
		this.dest = File.createTempFile("dest", ".xml");
		this.scheme = new File("src/main/resources/stylesheet.xsl");
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@After
	public void clear() {
		this.target.delete();
		this.dest.delete();
	}

	@Test
	public void whenConvertThenObtainCorrectDataFile() {
		String[] args = {"-t", this.dest.getPath(),
				"-s", this.target.getPath(),
				"-scheme", this.scheme.getPath(),
				"-q", "10"};
		var arguments = new Arguments(args).init();
		var app = new Main(arguments);
		app.createXML();
		var actual = app.count();
		assertThat(actual, is(55));
	}
}