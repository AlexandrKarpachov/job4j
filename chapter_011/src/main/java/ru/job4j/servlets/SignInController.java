package ru.job4j.servlets;

import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 20.10.2019
 */
public class SignInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/SignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter("login");
        var user = ValidateService.getInstance().findByLogin(
                new User.Builder().withLogin(login).build()
        );
        if (user == null) {
            req.setAttribute("error", "such login does not exist");
            req.getRequestDispatcher("WEB-INF/views/SignIn.jsp").forward(req, resp);
        } else if (!req.getParameter("password").equals(user.getPassword())) {
            req.setAttribute("error", "wrong password");
            req.getRequestDispatcher("WEB-INF/views/SignIn.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("login", user.getLogin());
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
