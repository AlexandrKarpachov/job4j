package ru.job4j.servlets;

import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 13.10.2019
 */
public class UsersServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("users", validate.findAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var id = Integer.parseInt(req.getParameter("id"));
        var user = this.validate.findById(new User(id));
        File folder = new File("images");
        String fileName = user.getPhotoId();
        if (fileName != null) {
            //noinspection ResultOfMethodCallIgnored
            new File(folder + File.separator + fileName).delete();
        }
        this.validate.delete(user);
        this.doGet(req, resp);
    }
}
