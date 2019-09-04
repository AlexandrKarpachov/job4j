package ru.job4j.calc;
/**
 * @author Aleksandr Karpachov
 * @version $Id$
 * @since 03.08.2019
 *
 * Interface which allows to put values from different sources.
 */
public interface Input {
	String ask(String msg);
}
