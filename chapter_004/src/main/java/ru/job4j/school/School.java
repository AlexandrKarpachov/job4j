package ru.job4j.school;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Aleksand Karpachov
 * @since 19.05.2019
 */
public class School {
    /**
     * Method  filters List of students by predicate
     * @param students list
     * @param predict for filter
     * @return list filtered by predicate
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(predict).collect(Collectors.toList());
    }

    public Map<String, Student> collectToMap(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Student::getSurname,
                        e -> e
                ));
    }

    /**
     * This method filters input student list by minimum score
     * @param students input student list. Can store null values
     * @param bound minimum score
     * @return sorted by score(descend order) -> surname -> name student list
     * with a minimum score equals to bound.
     */
    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(Comparator.nullsLast(Comparator.naturalOrder()))
                .flatMap(Stream::ofNullable)
                .takeWhile(student -> student.getScore() >= bound)
                .collect(Collectors.toList());
    }

}
