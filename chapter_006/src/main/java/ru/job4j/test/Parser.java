package ru.job4j.test;

import java.util.*;

public class Parser {

	private static final List<Character> BRACES = List.of('{', '}');

	/**
	 * return all possible, correct variants of braces locate,
	 * and removes excess
	 */
	public List<String> parse(String input) {
		var result = new HashSet<String>();
		input = this.strict(input);
		var bracesCount = this.amountOfBlocks(input) * 2;
		var bracesPos = this.positions(input);
		var samples = this.generateCombinations(bracesPos.size(), bracesCount, input);
		for (List<Integer> sample : samples) {
			var temp = input;
			var deleted = 0;
			for (int index : bracesPos) {
				var realIndex = index - deleted;
				if (!sample.contains(index)) {
					temp = temp.substring(0, realIndex) + temp.substring(realIndex + 1);
					deleted++;
				}
			}
			var isCorrect = this.validate(temp);
			if (isCorrect) {
				result.add(temp);
			}
		}
		return new ArrayList<>(result);
	}

	/**
	 * @param in input text.
	 * @return list with all braces positions
	 */
	private List<Integer> positions(String in) {
		var result = new ArrayList<Integer>();
		for (int i = 0; i < in.length(); i++) {
			var current = in.charAt(i);
			if (BRACES.contains(current)) {
				result.add(i);
			}
		}
		return result;
	}


	/**
	 * checks if input string is correct
	 */
	private boolean validate(String in) {
		var result = true;
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < in.length(); i++) {
			var current = in.charAt(i);
			if (current == '{') {
				stack.add(i);
			} else if (current == '}') {
				var close = stack.pollLast();
				if (close == null) {
					result = false;
					break;
				}
			}
		}
		if (!stack.isEmpty()) {
			result = false;
		}
		return result;
	}

	/**
	 * returns the number of blocks that should be in the string
	 */
	private int amountOfBlocks(String in) {
		var result = 0;
		Deque<Integer> stack = new LinkedList<>();
		for (int i = 0; i < in.length(); i++) {
			var current = in.charAt(i);
			if (current == '{') {
				stack.add(i);
			} else if (current == '}') {
				var close = stack.pollLast();
				if (close != null) {
					result++;
				}
			}
		}
		return result;
	}

	/**
	 * truncates the string from the extra brackets at the beginning and at the end
	 */
	private String strict(String in) {
		in = this.strictStart(in);
		in = this.strictEnd(in);
		return in;
	}

	private String strictStart(String in) {
		var start = in.indexOf('{');
		if (start == -1) {
			in = in.replace("}", "");
		} else {
			for (int i = 0; i < start; i++) {
				if (in.charAt(i) == '}') {
					in = in.substring(0, i) + in.substring(i + 1);
					i -= 1;
					start -= 1;
				}
			}
		}
		return in;
	}

	private String strictEnd(String in) {
		var end = in.lastIndexOf('}');
		if (end == -1) {
			in = in.replace("{", "");
		} else {
			for (int i = end + 1; i < in.length(); i++) {
				if (in.charAt(i) == '{') {
					in = in.substring(0, i) + in.substring(i + 1);
					i -= 1;
					end -= 1;
				}
			}
		}
		return in;
	}

	/**
	 * Creates array filled numbers from 0 to {@code size}
	 * @param size of array
	 */
	private int[] arrayInit(int size) {
		var arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = i;
		}
		return arr;
	}

	/**
	 * generates all combinations of braces, in such a way that
	 * each combination stores the number of indexes that are necessary
	 * to construct a correct expression.
	 * @param totalAmount number of braces in input expression
	 * @param pickSize number of braces which are necessary to construct
	 *                 a correct expression.
	 * @param in input expression.
	 */
	private ArrayList<List<Integer>> generateCombinations(int totalAmount, int pickSize, String in) {
		var first = this.arrayInit(pickSize);
		var positions = positions(in);
		var sample = this.getIndexList(first.clone(), this.positions(in));
		Queue<int[]> stack = new LinkedList<>(List.of(first));
		var result = new ArrayList<List<Integer>>();
		result.add(getIndexList(first, sample));
		while (!stack.isEmpty()) {
			var arr = stack.poll();
			for (int i = pickSize - 1; i >= 0; i--) {
				if (arr[i] < totalAmount - pickSize + i) {
					arr[i]++;
					for (int j = i; j < pickSize - 1; j++) {
						arr[j + 1] = arr[j] + 1;
					}
					stack.add(arr);
					result.add(getIndexList(arr, positions));
					break;
				}
			}
		}
		return result;
	}

	private List<Integer> getIndexList(int[] arr, List<Integer> positions) {
		var result = new ArrayList<Integer>();
		for (int num : arr) {
			result.add(positions.get(num));
		}
		return result;
	}
}

