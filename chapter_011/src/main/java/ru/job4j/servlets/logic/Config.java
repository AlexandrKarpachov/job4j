package ru.job4j.servlets.logic;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 25.07.2019
 */
public class Config {
	private final Properties values = new Properties();

	/**
	 * load all properties from the file
	 */
	public Config init() {
		try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
			//noinspection all
			values.load(in);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return this;
	}

	/**
	 * returns properties by key.
	 * !<b>Attention</b> works only after using {@link this#init()}
	 */
	public String get(String key) {
		return this.values.getProperty(key);
	}
}