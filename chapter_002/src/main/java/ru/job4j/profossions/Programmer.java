package ru.job4j.profossions;
import ru.job4j.profossions.auxiliary.*;
public class Programmer extends Profession{
    private String languige;

    public Programmer (String name, int stage, int salary, String languige) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
    }

    public String getLanguige() {
        return languige;
    }

    public void fixBug(Bug bug) {}

    public Programm makeProgramm(Task task) {return new Programm();}
}
