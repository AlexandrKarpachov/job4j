package ru.job4j.io;

import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void loadFileThenGetByKey() {
        var config = new Config("app.properties.txt");
        config.load();

        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
    }



}