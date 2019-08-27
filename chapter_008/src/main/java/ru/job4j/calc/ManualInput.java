package ru.job4j.calc;

import java.util.List;

/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 03.08.2019
 *
 * Class for testing the app. Allows manually pass values
 */
public class ManualInput implements Input {
	private int cursor = 0;
	private List<String> expression;

	public ManualInput(List<String> expression) {
		this.expression = expression;
	}

	@Override
	public String ask(String msg) {
		String result;
		if (cursor >= expression.size()) {
			result = null;
		} else {
			result = expression.get(cursor++);
		}
		return result;
	}
}
