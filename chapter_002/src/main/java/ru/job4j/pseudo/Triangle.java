package ru.job4j.pseudo;

public class Triangle implements Shape {
    @Override
    public String draw() {
        StringBuilder trn = new StringBuilder();
        trn.append("   +   \n");
        trn.append("  +++  \n");
        trn.append(" +++++ \n");
        trn.append("+++++++\n");
        return trn.toString();
    }
}
