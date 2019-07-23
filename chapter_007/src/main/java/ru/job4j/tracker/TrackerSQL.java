package ru.job4j.tracker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;
import java.util.stream.Collectors;


public class TrackerSQL implements ITracker, AutoCloseable {
	private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
	private Connection connection;
	private static String table;
	private static  class Query {
		private static final String CREATE_NEW_ITEM =
				String.format("INSERT INTO %s (item_name, description, cr_date) VALUES (?, ?, ?);", table);
		private static final String DELETE_ITEM = String.format("DELETE FROM %s WHERE id = ?;", table);
		private static final String UPDATE_ITEM =
				String.format("UPDATE %s SET item_name = ?, description = ?, cr_date = ? WHERE id = ?;", table);
		private static final String GET_ALL = String.format("SELECT * FROM %s;", table);
		private static final String GET_BY_ID = String.format("SELECT * FROM %s where id = ?;", table);
		private static final String GET_BY_NAME = String.format("SELECT * FROM %s where item_name = ?;", table);
	}

	public boolean init() {
		try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
			Properties config = new Properties();
			//noinspection ConstantConditions
			config.load(in);
			TrackerSQL.table = config.getProperty("table");
			Class.forName(config.getProperty("driver-class-name"));
			this.connection = DriverManager.getConnection(
					config.getProperty("url"),
					config.getProperty("username"),
					config.getProperty("password")
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		var result = this.connection != null;
		if (result) {
			this.createTable();
		}
		return result;
	}


	public void createTable()  {
		//noinspection ConstantConditions
		var file = TrackerSQL.class.getClassLoader().getResource("CreateTables.sql").getFile();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			Statement stmt = this.connection.createStatement();
			stmt.execute(br.lines().collect(Collectors.joining(" \n")));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void close()  {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public Item add(Item item) {
		var time = new Timestamp(item.getTime());
		var name = item.getName();
		var desc = item.getDesc();
		try (var st = this.connection.prepareStatement(
				Query.CREATE_NEW_ITEM, PreparedStatement.RETURN_GENERATED_KEYS)) {
			st.setString(1, name);
			st.setString(2, desc);
			st.setTimestamp(3, time);
			st.executeUpdate();
			try (var generatedId = st.getGeneratedKeys()) {
				while (generatedId.next()) {
					item.setId(String.valueOf(generatedId.getInt(1)));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return item;
	}

	@Override
	public boolean replace(String id, Item item) {
		var  result = false;
		try (var statement = this.connection.prepareStatement(Query.UPDATE_ITEM)) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDesc());
			statement.setTimestamp(3, new Timestamp(item.getTime()));
			statement.setInt(4, Integer.parseInt(id));
			var deleted = statement.executeUpdate();
			if (deleted > 0) {
				result = true;
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public boolean delete(String id) {
		var  result = false;
		try (var statement = this.connection.prepareStatement(Query.DELETE_ITEM)) {
			statement.setInt(1, Integer.parseInt(id));
			var deleted = statement.executeUpdate();
			if (deleted > 0) {
				result = true;
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<Item> findAll() {
		var result = new ArrayList<Item>();
		try (var conn = this.connection.createStatement()) {
			var reqResult = conn.executeQuery(Query.GET_ALL);
			while (reqResult.next()) {
				result.add(this.createItem(reqResult));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<Item> findByName(String key) {
		var result = new ArrayList<Item>();
		try (var statement = this.connection.prepareStatement(Query.GET_BY_NAME)) {
			statement.setString(1, key);
			var reqResult = statement.executeQuery();
			while (reqResult.next()) {
				result.add(this.createItem(reqResult));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Item findById(String id) {
		Item result = null;
		try (var statement = this.connection.prepareStatement(Query.GET_BY_ID)) {
			statement.setInt(1, Integer.parseInt(id));
			var reqResult = statement.executeQuery();
			while (reqResult.next()) {
				result = createItem(reqResult);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}

	private Item createItem(ResultSet set) {
		Item result = null;
		try {
			var id = set.getString("id");
			var time = set.getTimestamp("cr_date").getTime();
			var name = set.getString("item_name");
			var desc = set.getString("description");
			result = new Item(name, desc, time);
			result.setId(id);
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
}
