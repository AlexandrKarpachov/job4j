package ru.job4j.calc;

import java.util.Scanner;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 03.08.2019
 *
 */
public class ConsoleInput implements Input {

	/**
	 * The method prints input massage, and read values from console.
	 * @param msg for printing.
	 * @return read value.
	 */
	@Override
	public String ask(String msg) {
		var scanner = new Scanner(System.in);
		System.out.println(msg);
		return scanner.next();
	}
}
