package ru.job4j.streamApi;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SchoolTest {

    @Test
    public void whenFilteredAndCollectedLessThen50() {
        List<Student> students = Arrays.asList(
                new Student(45),
                new Student(47),
                new Student(75),
                new Student(50),
                new Student(70)
        );
        List<Student> expect = Arrays.asList(
                new Student(45),
                new Student(47)
        );
        School school = new School();
        List<Student> actual = school.collect(students, student -> {
                    boolean result = false;
                    if (student.getScore() < 50) {
                        result = true;
                    }
                    return result;
                });
        assertThat(actual, is(expect));
    }

    @Test
    public void whenFilteredAndCollectedLessBetween50And70() {
        List<Student> students = Arrays.asList(
                new Student(45),
                new Student(65),
                new Student(75),
                new Student(50),
                new Student(70)
        );
        List<Student> expect = Arrays.asList(
                new Student(65),
                new Student(50)
        );
        School school = new School();
        List<Student> actual = school.collect(students, student -> {
            boolean result = false;
            int score = student.getScore();
            if (score >= 50 && score < 70){
                result = true;
            }
            return result;
        });
        assertThat(actual, is(expect));
    }

    @Test
    public void whenFilteredAndCollectedBiggerThen69() {
        List<Student> students = Arrays.asList(
                new Student(45),
                new Student(47),
                new Student(75),
                new Student(50),
                new Student(70)
        );
        List<Student> expect = Arrays.asList(
                new Student(75),
                new Student(70)
        );
        School school = new School();
        List<Student> actual = school.collect(students, student -> {
            boolean result = false;
            if (student.getScore() >= 70){
                result = true;
            }
            return result;
        });
        assertThat(actual, is(expect));
    }

}
