package ru.job4j.school;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
}
