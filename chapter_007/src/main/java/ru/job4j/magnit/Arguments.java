package ru.job4j.magnit;

import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * Store Arguments for program executing
 * !Before using this class you have to use {@link this#init()} method.
 *
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 28.07.2019
 */
public class Arguments {
	private static final List<String> SOURCE_TAGS = List.of("-s", "-source");
	private static final List<String> TARGET_TAGS = List.of("-t", "-target", "-destination");
	private static final List<String> SCHEME_TAGS = List.of("-sh", "-scheme");
	private static final List<String> QUANTITY_TAGS = List.of("-q", "-quantity", "-size");
	private int size;
	private String source;
	private String target;
	private String scheme;
	String[] args;

	public Arguments(String[] args) {
		this.args = args;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

	public String getScheme() {
		return scheme;
	}

	public int getSize() {
		return size;
	}


	public Arguments init() {
		this.source = this.argValue(SOURCE_TAGS);
		this.target = this.argValue(TARGET_TAGS);
		this.scheme = this.argValue(SCHEME_TAGS);
		this.size = Integer.parseInt(this.argValue(QUANTITY_TAGS));
		return this;
	}

	/**
	 * checks incoming data for correctness
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public boolean check() {
		var source = new File(this.source);
		var target = new File(this.target);
		var scheme = new File(this.scheme);
		try {
			source.createNewFile();
			target.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return source.isFile() && target.isFile() && scheme.isFile()
				&& this.size > 0;
	}

	private String argValue(List<String> commands) {
		String result = "";
		for (int i = 0; i < this.args.length - 1; i++) {
			if (commands.contains(args[i])) {
				result = args[i + 1];
				break;
			}
		}
		return result;
	}
}
