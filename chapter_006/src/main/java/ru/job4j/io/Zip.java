package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	private Args arg;

	public Zip(Args arguments) {
		this.arg = arguments;
	}

	public void pack(List<File> sources, File target) {
		var root = new File(arg.directory());
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
			for (File file : sources) {
				zip.putNextEntry(new ZipEntry(file.getPath().substring(root.getParent().length())));
				try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
					zip.write(out.readAllBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<File> seekBy(String root, String ext) {
		var rootDir = new File(root);
		var result = new ArrayList<File>();
		Queue<File> directories = new LinkedList<>();
		if (rootDir.isDirectory()) {
			directories.add(rootDir);
		}
		while (!directories.isEmpty()) {
			var file = directories.poll();
			var fileList = file.listFiles();
			//noinspection ConstantConditions
			for (File f : fileList) {
				if (f.isDirectory()) {
					directories.add(f);
				} else if (!f.getName().endsWith("." + ext)) {
					result.add(f);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		var zip = new Zip(new Args(args));
		var fileList = zip.seekBy(zip.arg.directory(), zip.arg.exclude());
		zip.pack(fileList, new File(zip.arg.output()));
	}

	private static class Args {
		private final String[] args;

		private Args(String[] args) {
			this.args = args;
		}

		public String directory() {
			return this.argValue("-d");
		}

		public String exclude() {
			String ext = this.argValue("-e");
			int index = ext.indexOf(".");
			return ext.substring(index + 1);
		}

		public String output() {
			return this.argValue("-o");
		}

		private String argValue(String command) {
			String result = "";
			for (int i = 0; i < this.args.length - 1; i++) {
				if (args[i].equals(command)) {
					result = args[i + 1];
					break;
				}
			}
			return result;
		}
	}
}