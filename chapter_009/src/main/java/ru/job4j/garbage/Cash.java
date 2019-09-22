package ru.job4j.garbage;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Cash {
	private Map<String, SoftReference<String>> files = new HashMap<>();
	private final String path;

	public Cash(String path) {
		this.path = path;
	}

	public String get(String key) throws IOException {
		String result;
		if (this.files.containsKey(key) && this.files.get(key) != null) {
			result = files.get(key).get();
		} else {
			result = new String(Files.readAllBytes(Paths.get(path, key)));
			this.files.put(key, new SoftReference<>(result));
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		Cash cash = new Cash("C:/projects/job4j/chapter_009/src/main/resources");
		String result = cash.get("address.txt");
		System.out.println(result);
	}
}
