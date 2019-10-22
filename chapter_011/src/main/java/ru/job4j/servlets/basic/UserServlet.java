package ru.job4j.servlets.basic;

import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();
    private final ConcurrentHashMap<String, Function<User, Boolean>> actions = new ConcurrentHashMap<>();

    @Override
    public void init() {
        actions.put("add", validate::add);
        actions.put("update", validate::update);
        actions.put("delete", validate::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        this.validate.findAll().stream()
                .map(User::toString)
                .forEach(writer::append);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        User user = this.createUser(req);
        boolean result = actions.get(action).apply(user);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (result) {
            writer.append(String.format(
                    "Congratulations, you add a new user %nID = %s%nLogin = %s",
                    user.getId(), user.getLogin()
            ));
        } else {
            writer.append("Sorry, you entered a wrong data");
        }
        writer.flush();
    }


    private User createUser(HttpServletRequest req) {
        var textId = req.getParameter("id");
        var id = textId == null ? validate.generateID() : Integer.parseInt(textId);
        return new User.Builder()
                .withID(id)
                .withLogin(req.getParameter("login"))
                .withName(req.getParameter("name"))
                .withEmail(req.getParameter("email"))
                .build();
    }


}
