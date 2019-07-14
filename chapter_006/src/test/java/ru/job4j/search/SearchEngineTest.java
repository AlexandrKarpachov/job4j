package ru.job4j.search;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SearchEngineTest {
	private String root;
	private String result;

	@Before
	public void creatingFiles() throws IOException {
		var temp = System.getProperty("java.io.tmpdir");
		var root = new File(temp, "root");
		var dir1 = new File(root, "firstDir");
		var nestedDir1 = new File(dir1, "nested");
		var nestedDir2 = new File(dir1, "nested2");
		var dirs = new File[] {root, dir1, nestedDir1, nestedDir2};
		Arrays.stream(dirs).forEach(File::mkdirs);
		this.root = root.getAbsolutePath();
		this.result = new File(root, "result.txt").getAbsolutePath();
		new File(root, "first.txt").createNewFile();
		new File(dir1, "second.log").createNewFile();
		new File(nestedDir1, "nestedfirst.txt").createNewFile();
		new File(nestedDir1, "fourNested.txt").createNewFile();
	}

	@Test
	public void whenSearchByMaskModeThanOK() throws IOException {
		var args = new Arguments(new String[] {
				"-d", this.root, "-m", "-n", "*i?st.txt", "-o", this.result
		});
		var expected = List.of("first.txt", "nestedfirst.txt");
		this.testSearch(args, expected);
	}

	@Test
	public void whenSearchByMaskRegexNameThanOK() throws IOException {
		var args = new Arguments(new String[] {
				"-d", this.root, "-r", "-n", "(log)", "-o", this.result
		});
		var expected = List.of("second.log");
		this.testSearch(args, expected);
	}

	@Test
	public void whenSearchByMaskFullNameThanOK() throws IOException {
		var args = new Arguments(new String[] {
				"-d", this.root, "-f", "-n", "first.txt", "-o", this.result
		});
		var expected = List.of("first.txt");
		this.testSearch(args, expected);
	}

	@Test
	public void whenCheckCorrectArgsThenOK() {
		var args = new Arguments(new String[] {
				"-d", this.root, "-f", "-n", "first.txt", "-o", this.result
		});
		var cheker = new ArgChecker();

		assertThat(cheker.check(args), is(true));
	}

	@Test
	public void whenWrongNameArgsThenFalse() {
		var args = new Arguments(new String[] {
				"-d", this.root, "-f", "wrong key", "first.txt", "-o", this.result
		});
		var cheker = new ArgChecker();
		assertThat(cheker.check(args), is(false));
	}

	@Test
	public void whenWrongModeArgsThenFalse() {
		var args = new Arguments(new String[] {
				"-d", this.root, "wrong key", "-n", "first.txt", "-o", this.result
		});
		var cheker = new ArgChecker();
		assertThat(cheker.check(args), is(false));
	}

	private void testSearch(Arguments args, List<String> expected) throws IOException {
		new SearchEngine(args).init().search();
		var result = FileUtils.readLines(new File(this.result), Charsets.UTF_8);

		assertThat(result, is(expected));
	}


	@After
	public void clean() throws IOException {
		FileUtils.deleteDirectory(new File(this.root));
	}
}