package ru.job4j.profossions;
import ru.job4j.profossions.auxiliary.*;
public class Teacher extends Profession{
    String subject;
    public Teacher (String name, int stage, int salary, String subject) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void conductLesson(Theme theme) {}

    public void fillJournal(Journal jr) {}
}
