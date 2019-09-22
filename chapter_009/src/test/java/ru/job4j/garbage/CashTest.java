package ru.job4j.garbage;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CashTest {

	@Test
	public void whenUseGetMethodThenGotFileContent() throws IOException {
		File file = new File(
				getClass().getClassLoader().getResource("names.txt").getFile()
		);
		var cash = new Cash(file.getParent());

		var result = cash.get("names.txt");

		assertThat(result, is("names"));
	}

	@Test(expected = IOException.class)
	public void whenPutWrongFileNameThenException() throws IOException {
		File file = new File(
				getClass().getClassLoader().getResource("names.txt").getFile()
		);
		var cash = new Cash(file.getParent());

		var result = cash.get("namees.txt");
	}
}