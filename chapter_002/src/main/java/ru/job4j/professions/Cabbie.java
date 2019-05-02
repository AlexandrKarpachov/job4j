package ru.job4j.professions;
import ru.job4j.professions.auxiliary.*;

public class Cabbie extends Profession {
    public Cabbie(String name, int stage, int salary) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
    }

    public void bring(Address adr) { }

    public void deliver(Address adr) { }
}
