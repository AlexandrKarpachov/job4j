package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
    /**
     * This field stored stored sequence User answers
     * For example. If user want to pick
     * add a new application, he needs to enter:
     * 0 - pick paragraph of menu "add a new application".
     * name - name of application
     * desc - describe of application
     * y - exit the tracker
     */

    private final String[] value;

    /**
     * The field counts quantity as a call to the ask method.
     */
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Давайте рассмотрим, как работает этот метод.
     * у нас есть объект в котором содержатся заранее продуманные ответы.
     * При последовательном вызове метода ask нам надо возвращать соответствующие данные.
     * Как если бы мы симулировали поведение пользователя.
     * Для этого при каждом вызове метода ask мы увеличиваем счетчик и
     * при следующем вызове он вернет нам новое значение.
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int result = -1;
        int key = Integer.parseInt(this.value[position++]);
        for (int value : range) {
            if (key == value) {
                result = key;
            }
        }
        if (result == -1) {
            throw new MenuOutException("Invalid value, out of menu range!");
        }
        return result;
    }
}