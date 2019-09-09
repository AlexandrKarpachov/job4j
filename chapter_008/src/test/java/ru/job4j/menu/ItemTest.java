package ru.job4j.menu;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
	Menu menu = new Menu("Menu:");
	MenuItem root1 = new MenuItem("1", "root1");
	MenuItem root2 = new MenuItem("2", "root2");
	MenuItem nested1 = new MenuItem("1.1", "nested1");
	MenuItem nested2 = new MenuItem("1.2", "nested2");
	MenuItem doubleNested1 = new MenuItem("1.1.1", "nested1");
	MenuItem doubleNested2 = new MenuItem("1.1.2", "nested2");

	@Before
	public void init() {
		this.root1.addItems(List.of(nested1, nested2));
		this.nested1.addItems(List.of(doubleNested1, doubleNested2));
		this.menu.addItem(root1);
		this.menu.addItem(root2);
	}

	@Test
	public void checkMenuWithNestedItems() {
		var expected = String.join(System.lineSeparator(),
				"Menu:",
						 "1 root1",
						 "\t1.1 nested1",
						 "\t\t1.1.1 nested1",
						 "\t\t1.1.2 nested2",
						 "\t1.2 nested2",
						 "2 root2" + System.lineSeparator()
		);

		var actual = menu.show();

		assertThat(actual, is(expected));
	}


	@Test
	public void whenGetItemByKeyThenOk() {
		var actual = this.menu.getItem("1.2");

		assertThat(actual.getName(), is("nested2"));
	}


	@Test
	public void whenPerformActionByKeyThenOk() {
		PrintStream stdOut = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		this.nested1.setAction(new Printer("wow!"));

		this.menu.getItem("1.1").perform();
		var actual = out.toString();

		assertThat(actual, is("wow!"));
		System.setOut(stdOut);
	}

	class Printer implements ActionListener {
		private String msg;

		public Printer(String msg) {
			this.msg = msg;
		}

		@Override
		public void performAction() {
			System.out.print(msg);
		}
	}
}