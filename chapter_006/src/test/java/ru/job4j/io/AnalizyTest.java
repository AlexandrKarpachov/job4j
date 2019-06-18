package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    String tmp;

    @Before
    public void before() throws FileNotFoundException {
        tmp = System.getProperty("java.io.tmpdir");
        PrintWriter out = new PrintWriter(new FileOutputStream(tmp + "server.log"));
        out.println("200 10:56:01");
        out.println(System.lineSeparator());
        out.println("500 10:57:01");
        out.println(System.lineSeparator());
        out.println("400 10:58:01");
        out.println(System.lineSeparator());
        out.println("200 10:59:01");
        out.println(System.lineSeparator());
        out.println("500 11:01:02");
        out.println(System.lineSeparator());
        out.println("200 11:02:02");
        out.println(System.lineSeparator());
        out.close();
    }

    @Test
    public void whenLogContains3PeriodsShould3Periods() throws FileNotFoundException {
        Analizy analizy = new Analizy();
        analizy.unavailable(tmp + "server.log", tmp + "target.csv");
        Scanner scanner = new Scanner(new FileInputStream(tmp + "target.csv"));
        assertThat(scanner.nextLine(), is("10:57:01;10:59:01"));
        assertThat(scanner.nextLine(), is("11:01:02;11:02:02"));
        scanner.close();
    }

    @After
    public void after() {
        File file = new File(tmp + "server.log");
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        file = new File(tmp + "target.csv");
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
    }

}
