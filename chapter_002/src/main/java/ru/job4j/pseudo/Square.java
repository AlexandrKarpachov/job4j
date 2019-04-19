package ru.job4j.pseudo;

public class Square implements Shape {
    @Override
    public String draw() {
        StringBuilder sqr = new StringBuilder();
        sqr.append("+++++++\n");
        sqr.append("+++++++\n");
        sqr.append("+++++++\n");
        sqr.append("+++++++\n");

        return sqr.toString();
    }
}
