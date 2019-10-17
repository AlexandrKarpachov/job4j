package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 14.10.2019
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = ValidateService.getInstance().findById(new User(id, null, null, null));
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/views/Edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("createDate")
        );
        this.validate.update(user);
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
