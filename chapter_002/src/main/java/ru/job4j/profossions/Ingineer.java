package ru.job4j.profossions;
import ru.job4j.profossions.auxiliary.*;
public class Ingineer extends Profession {

    public Ingineer (String name, int stage, int salary) {
        this.name = name;
        this.salary = salary;
        this.stage = stage;
    }

    public Blueprint createBluprinte(Task task) {return new Blueprint();}

    public Calculation makeCalcalation(Data data) {return new Calculation();}

}
