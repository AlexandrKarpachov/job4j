package ru.job4j.array;

/**
 * Обертка над строкой.
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        int length = prefix.length() < data.length ? prefix.length() : data.length;
        char[] value = prefix.toCharArray();
        for (int i = 0; i < length; i++) {
            if (this.data[i] != value[i]) {
                return false;
            }
        }
        return result;
    }
}