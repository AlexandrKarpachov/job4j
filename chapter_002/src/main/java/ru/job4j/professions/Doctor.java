package ru.job4j.professions;
import ru.job4j.professions.auxiliary.*;
public class Doctor extends Profession {
    String specialization;
    public Doctor(String name, int stage, int salary, String spec) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
        this.specialization = spec;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void heal(Pacient pacient) { }

    public void makeInjection(Medication med) { }
}
