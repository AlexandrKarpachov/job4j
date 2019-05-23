package ru.job4j.school;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SchoolTest {

    @Test
    public void whenFilteredAndCollectedLessThen50() {
        List<Student> students = Arrays.asList(
                new Student("Ivan", "Surname1", 45),
                new Student("Vasyl", "Surname1", 47),
                new Student("Petr", "Surname1", 75),
                new Student("Gennadiy", "Surname1", 50),
                new Student("Aleksey", "Surname1", 70)
        );
        List<Student> expect = Arrays.asList(
                new Student("Ivan", "Surname1", 45),
                new Student("Vasyl", "Surname1", 47)
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
                new Student("Ivan", "Surname1", 45),
                new Student("Vasyl", "Surname1", 65),
                new Student("Petr", "Surname1", 75),
                new Student("Gennadiy", "Surname1", 50),
                new Student("Aleksey", "Surname1", 70)
        );
        List<Student> expect = Arrays.asList(
                new Student("Vasyl", "Surname1", 65),
                new Student("Gennadiy", "Surname1", 50)
        );
        School school = new School();
        List<Student> actual = school.collect(students, student -> {
            boolean result = false;
            int score = student.getScore();
            if (score >= 50 && score < 70) {
                result = true;
            }
            return result;
        });
        assertThat(actual, is(expect));
    }

    @Test
    public void whenFilteredAndCollectedBiggerThen69() {
        List<Student> students = Arrays.asList(
                new Student("Ivan", "Surname1", 45),
                new Student("Vasyl", "Surname1", 47),
                new Student("Petr", "Surname1", 75),
                new Student("Gennadiy", "Surname1", 50),
                new Student("Aleksey", "Surname1", 70)
        );
        List<Student> expect = Arrays.asList(
                new Student("Petr", "Surname1", 75),
                new Student("Aleksey", "Surname1", 70)
        );
        School school = new School();
        List<Student> actual = school.collect(students, student -> {
            boolean result = false;
            if (student.getScore() >= 70) {
                result = true;
            }
            return result;
        });
        assertThat(actual, is(expect));
    }

    @Test
    public void whenListStudentsThenGetMap() {
        var school = new School();
        var students = Arrays.asList(
                new Student("Ivan", "Ivanov", 45),
                new Student("Vasyl", "Grishin", 47),
                new Student("Petr", "Perviy", 75)
        );
        var vanya = new Student("Ivan", "Ivanov", 45);
        var vasya = new Student("Vasyl", "Grishin", 47);
        var petya = new Student("Petr", "Perviy", 75);
        var expect = new HashMap<String, Student>();
        expect.put("Ivanov", vanya);
        expect.put("Grishin", vasya);
        expect.put("Perviy", petya);
        var actual = school.collectToMap(students);
        assertThat(actual, is(expect));
    }

    @Test
    public void whenPutListWithNullThanGetSorted() {
        var school = new School();
        var students = Arrays.asList(
                null,
                new Student("Ivan", "Ivanov", 45),
                new Student("Vasyl", "Grishin", 50),
                null,
                new Student("Petr", "Perviy", 75)
        );

        var expect = Arrays.asList(
                new Student("Petr", "Perviy", 75),
                new Student("Vasyl", "Grishin", 50)
        );
        var actual = school.levelOf(students, 50);
        assertThat(expect, is(actual));
    }

    @Test
    public void whenPutOnlyNullThanGetEmpty() {
        var school = new School();
        List<Student> students = Arrays.asList(
                null,
                null,
                null
        );
        var expect = new ArrayList<Student>();
        var actual = school.levelOf(students, 35);
        assertThat(expect, is(actual));
    }
}
