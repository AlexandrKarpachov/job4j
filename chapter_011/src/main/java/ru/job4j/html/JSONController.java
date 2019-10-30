package ru.job4j.html;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 24.10.2019
 */
public class JSONController extends HttpServlet {
    private final CopyOnWriteArrayList<User> list = new CopyOnWriteArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var out = resp.getOutputStream();
        mapper.writeValue(out, list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(reader, User.class);
        System.out.println(user);
        list.add(user);
    }

}
