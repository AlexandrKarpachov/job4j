package ru.job4j.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 26.07.2019
 *
 */
public class StoreSQL implements AutoCloseable {
	private final Config config;
	private Connection connect;
	private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);

	private enum Query {
		CREATE_TABLE("CREATE TABLE Entry (field INTEGER PRIMARY KEY)"),
		DELETE_TABLE("DROP TABLE Entry"),
		INSERT("INSERT INTO Entry (field) VALUES (?)"),
		SELECT_ALL("SELECT * FROM Entry"),
		COUNT("SELECT COUNT(*) FROM Entry");
		public final String query;

		Query(String query) {
			this.query = query;
		}
	}

	public StoreSQL(Config config) {
		this.config = config;
	}

	/**
	 * creates connection with database and creates table within it.
	 * @return instance of this class with which you can work.
	 */
	public StoreSQL init() {
		try {
			this.connect = DriverManager.getConnection(config.get("url"));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		try (var stmt = this.connect.createStatement()) {
			stmt.executeUpdate(Query.CREATE_TABLE.query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return this;
	}

	/**
	 * feels table ib database by values
	 * @param size number of values.
	 */
	public void generate(int size) {
		Savepoint savepoint = null;
		try (var statement = this.connect.prepareStatement(Query.INSERT.query)) {
			savepoint = connect.setSavepoint();
			this.connect.setAutoCommit(false);
			this.clearTable();
			for (int i = 1; i <= size; i++) {
				statement.setInt(1, i);
				statement.executeUpdate();
			}
			this.connect.commit();
		} catch (SQLException e) {
			try {
				LOG.error(e.getMessage(), e);
				this.connect.rollback(savepoint);
			} catch (SQLException ex) {
				LOG.error(e.getMessage(), e);
			}
		} finally {
			try {
				this.connect.setAutoCommit(true);
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * clear table by deleting and creating a new instance of this table
	 */
	private void clearTable() {
		try (var stmt = this.connect.createStatement()) {
			stmt.executeUpdate(Query.DELETE_TABLE.query);
			stmt.executeUpdate(Query.CREATE_TABLE.query);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * Loads all values from table and creates from them Entries
	 * @return List with all entries which store in table.
	 */
	public List<Entry> load() {
		var result = new ArrayList<Entry>();

		try (var stmt = this.connect.createStatement()) {
			var count = stmt.executeQuery(Query.COUNT.query);
			while (count.next()) {
				result.ensureCapacity(count.getInt(1));
			}
			var set = stmt.executeQuery(Query.SELECT_ALL.query);
			while (set.next()) {
				result.add(new Entry(set.getInt("field")));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}


	@Override
	public void close() throws Exception {
		if (connect != null) {
			connect.close();
		}
	}
}