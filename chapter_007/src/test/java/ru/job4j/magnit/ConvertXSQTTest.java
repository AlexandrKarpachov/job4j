package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConvertXSQTTest {

	StoreSQL generator;
	File target;
	File dest;
	File scheme;
	StoreXML saver;
	ConvertXSQT converter;

	@Before
	public void init() throws IOException {
		this.generator = new StoreSQL(new Config().init()).init();
		this.target = File.createTempFile("target", ".xml");
		this.dest = File.createTempFile("dest", ".xml");
		this.scheme = new File("src/main/resources/stylesheet.xsl");
		this.saver = new StoreXML(target);
		this.converter = new ConvertXSQT();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@After
	public void clear() {
		this.target.delete();
		this.dest.delete();
	}


	@Test
	public void whenConvertThenObtainCorrectDataFile() throws IOException {
		var expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry href=\"1\"/><entry href=\"2\"/></entries>";

		this.generator.generate(2);
		this.saver.save(this.generator.load());
		this.converter.convert(target, dest, scheme);
		var actual = Files.readString(dest.toPath());

		assertThat(actual, is(expected));
	}


}