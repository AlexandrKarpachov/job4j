package ru.job4j.professions;
import ru.job4j.professions.auxiliary.*;
public class Programmer extends Profession {
    private String language;

    public Programmer(String name, int stage, int salary, String language) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
    }

    public String getLanguage() {
        return language;
    }

    public void fixBug(Bug bug) { }

    public Programm makeProgram(Task task) {
        return new Programm();
    }
}
