package ru.job4j.chess.exceptions;

public class FigureNotFoundException extends Exception {
    public FigureNotFoundException() {
        super();
    }
    public FigureNotFoundException(String msg) {
        super("msg");
    }
}
