package ru.job4j.pseudo;

public class Paint {

    public static void main(String[] args) {
        Paint painter = new Paint();
        painter.draw(new Triangle());
        painter.draw(new Square());
    }
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
