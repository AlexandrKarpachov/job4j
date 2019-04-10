package ru.job4j.condition;

public class MultiMax {
    /**
     * Method for determining the maximum of three numbers
     * @param first number
     * @param second number
     * @param third number
     * @return maximum
     */
    public int max(int first, int second, int third) {
        int result = first > second ? first : second;
        return  result > third ? result : third;
    }
}
