package ru.job4j.school;

import java.util.Objects;
/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 * Class for storing Student info
 */
public class Student {
    private int score;

    public Student(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return getScore() == student.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore());
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                '}';
    }
}
