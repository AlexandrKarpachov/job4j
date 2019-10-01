package ru.job4j.nonbloking;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 01.10.2019
 */
public class OptimisticException extends RuntimeException {
	public OptimisticException(String msg) {
		super(msg);
	}
}
