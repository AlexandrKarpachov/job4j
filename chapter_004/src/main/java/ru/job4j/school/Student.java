package ru.job4j.school;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 * Class for storing Student info
 */
public class Student implements Comparable<Student> {
    private int score;
    private String name;
    private String surname;

    public Student(String name, String surname, int score) {
        this.score = score;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
        return Objects.hash(getScore(), getName(), getSurname());
    }

    @Override
    public String toString() {
        return String.format("Student{name = %s, surname = %s, score = %d",
                name, surname, score);
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::getScore, Comparator.reverseOrder())
                .thenComparing(Student::getSurname)
                .thenComparing(Student::getName)
                .compare(this, o);
    }
}
