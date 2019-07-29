package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StoreSQLTest {
	StoreSQL store;
	Config config;

	@Before
	public void init() {
		this.config = new Config().init();
		this.store = new StoreSQL(config).init();
	}

	@After
	public void close() {
		try {
			this.store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenGenerateThenSelectFromBase() {
		this.store.generate(5);
		var actual = new ArrayList<Integer>();
		var expected = List.of(1, 2, 3, 4, 5);

		try (var connect = DriverManager.getConnection(config.get("url"));
		     var stmt = connect.createStatement()) {
			var rs = stmt.executeQuery("SELECT * FROM Entry");
			while (rs.next()) {
				actual.add(rs.getInt("field"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assertThat(actual, is(expected));
	}

	@Test
	public void whenGenerateAndLoadThenObtainCorrectList() {
		this.store.generate(2);
		var actual = this.store.load();
		var expected = List.of(new Entry(1), new Entry(2));

		assertThat(actual, is(expected));
	}

	@Test
	public void whenGenerateAndLoadZeroThenObtainEmptyList() {
		this.store.generate(0);
		var actual = this.store.load();
		var expected = new ArrayList<Entry>();

		assertThat(actual, is(expected));
	}

}

