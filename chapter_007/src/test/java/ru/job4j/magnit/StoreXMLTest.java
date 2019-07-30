package ru.job4j.magnit;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StoreXMLTest {

	@Test
	public void whenGenerateAndSaveThenObtainCorrectDataFile() throws IOException {
		var generator = new StoreSQL(new Config().init()).init();
		File temp = File.createTempFile("target", ".xml");
		var saver = new StoreXML(temp);
		var expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
				     + "<entries>\n"
				     + "    <entry>\n"
				     + "        <field>1</field>\n"
				     + "    </entry>\n"
				     + "    <entry>\n"
				     + "        <field>2</field>\n"
				     + "    </entry>\n"
				     + "</entries>\n";

		generator.generate(2);
		saver.save(generator.load());
		var actual = Files.readString(temp.toPath());

		assertThat(actual, is(expected));
		//noinspection ResultOfMethodCallIgnored
		temp.delete();
	}
}