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
public class UserCreateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("WEB-INF/views/Create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(
                this.validate.generateID(),
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("createDate")
        );
        var result = this.validate.add(user);
        if (result) {
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/create", req.getContextPath()));
        }
    }
}
