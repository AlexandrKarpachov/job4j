package ru.job4j.servlets.logic;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 15.10.2019
 *
 * Realization for DB
 */
public class DbStore implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(DbStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private final AtomicInteger counter;
    private enum Queries {
        INSERT ("INSERT INTO Users (id, login, name, email, createDate, photo_id, \"Role\", password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT do nothing "),
        DELETE("DELETE FROM Users WHERE id = ?"),
        SELECT_ALL("SELECT * FROM Users"),
        FIND_BY_ID("SELECT * FROM Users WHERE id = ?"),
        FIND_BY_LOGIN("SELECT * FROM Users WHERE login = ?"),
        UPDATE("UPDATE Users SET name=?, email=?, photo_id=? WHERE id=?"),
        MAX_ID("SELECT MAX(id) FROM users;");
        public final String query;

        Queries(String query) {
            this.query = query;
        }
    }

    private DbStore() {
        Config config = new Config().init();
        SOURCE.setUrl(config.get("url"));
        SOURCE.setUsername(config.get("username"));
        SOURCE.setPassword(config.get("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            Class.forName(config.get("driver-class-name"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        counter = new AtomicInteger(getLastID() + 1);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    private int getLastID() {
        var result = -1;
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()
        ) {
           var rs = st.executeQuery(Queries.MAX_ID.query);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int generateID() {
        return counter.getAndIncrement();
    }

    @Override
    public boolean add(User user) {
        var result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(Queries.INSERT.query)
        ) {
            st.setInt(1, user.getId());
            st.setString(2, user.getLogin());
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(user.getCreateDate()));
            st.setString(6, user.getPhotoId());
            st.setString(7, user.getRole().name());
            st.setString(8, user.getPassword());
            int row = st.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        var result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(Queries.UPDATE.query)
        ) {
            st.setInt(4, user.getId());
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPhotoId());
            int row = st.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        var result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(Queries.DELETE.query)
        ) {
            st.setInt(1, user.getId());
            int row = st.executeUpdate();
            if (row > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        var result = new ArrayList<User>();
        try (Connection connection = SOURCE.getConnection();
             var st = connection.prepareStatement(Queries.SELECT_ALL.query)
        ) {
            var rs = st.executeQuery();
            while (rs.next()) {
                var parsedRole = rs.getString("role");
                var role = parsedRole == null
                        ? Role.USER : Role.valueOf(parsedRole);
                result.add(new User.Builder()
                        .withID(rs.getInt("id"))
                        .withLogin(rs.getString("login"))
                        .withName(rs.getString("name"))
                        .withEmail(rs.getString("email"))
                        .withPhoto(rs.getString("photo_id"))
                        .withRole(role)
                        .withCreateDate(rs.getTimestamp("createDate").getTime())
                        .withPassword(rs.getString("password"))
                        .build()
                );
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findById(User user) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             var st = connection.prepareStatement(Queries.FIND_BY_ID.query)
        ) {
            st.setInt(1, user.getId());
            var rs = st.executeQuery();
            result = createUser(rs);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public User findByLogin(User user) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             var st = connection.prepareStatement(Queries.FIND_BY_LOGIN.query)
        ) {
            st.setString(1, user.getLogin());
            var rs = st.executeQuery();
            result = createUser(rs);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private User createUser(ResultSet rs) {
        User result = null;
        try {
            if (rs.next()) {
                var parsedRole = rs.getString("role");
                var role = parsedRole == null
                        ? Role.USER : Role.valueOf(parsedRole);
                var parsedTime = rs.getTimestamp("createDate");
                var time = parsedTime == null ? 0 : parsedTime.getTime();
                result = new User.Builder()
                        .withID(rs.getInt("id"))
                        .withLogin(rs.getString("login"))
                        .withName(rs.getString("name"))
                        .withEmail(rs.getString("email"))
                        .withPhoto(rs.getString("photo_id"))
                        .withRole(role)
                        .withCreateDate(time)
                        .withPassword(rs.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}