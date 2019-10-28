package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 28.10.2019
 */
public class CitiesController extends HttpServlet {
    private final ConcurrentSkipListSet<String> defaultCities = new ConcurrentSkipListSet<>();

    @Override
    public void init() {
        defaultCities.addAll(List.of(
                "Moscow",
                "Kiev",
                "Minsk"
                )
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonCities = mapper.writeValueAsString(defaultCities);
        var out = resp.getOutputStream();
        out.print(jsonCities);
        out.flush();
    }
}
