package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Server availability analysis.
 *
 * @author Aleksandr Karpachov
 *
 * @since 18.06.19
 */
public class Analizy {
    private List<String> crushTypes = List.of("400", "500");
    private List<String> normalTypes = List.of("200", "300");

    public void unavailable(String source, String target) {
        List<String> log = this.read(source);
        String start = null;
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (String line : log) {
                var pair = line.split(" ");
                if (start == null && crushTypes.contains(pair[0])) {
                    start = pair[1];
                } else if (start != null && normalTypes.contains(pair[0])) {
                    out.println(start + ";" + pair[1]);
                    start = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String> read(String source) {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            result = read.lines().filter(x -> x.matches("[0-9](.*)"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}