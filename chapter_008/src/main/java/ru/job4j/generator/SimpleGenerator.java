package ru.job4j.generator;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SimpleGenerator {
	private static final Pattern KEYS = Pattern.compile("\\$\\{([a-z0-9]+)}");

	public String generate(String pattern, Map<String, String> keys) {
		var result = pattern;
		var map = new HashMap<>(keys);
		var matcher = KEYS.matcher(pattern);
		var key = "";
		while (matcher.find()) {
			key = matcher.group(1);
			if (!keys.containsKey(key)) {
				throw new NotFoundKeyException("missed key=" + key);
			}
			result = result.replace(matcher.group(0), keys.get(key));
			map.remove(key);
		}
		if (!map.isEmpty()) {
			throw new UnusedKeyException("Extra keys: " + map.toString());
		}
		return result;
	}
}
