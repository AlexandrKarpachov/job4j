package ru.job4j.servlets;

import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 14.10.2019
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = (String) req.getSession().getAttribute("login");
        User sessionUser = validate
                .findByLogin(new User.Builder().withLogin(login).build());
        var roles = new ArrayList<String>();
        if (sessionUser.getRole() == Role.ADMIN) {
            for (Role role : Role.values()) {
                roles.add(role.name());
            }

        } else {
            roles.add(Role.USER.name());
        }
        User user = validate.findById(new User(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("roles", roles);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/views/Edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User.Builder()
                .withID(Integer.parseInt(req.getParameter("id")))
                .withLogin(req.getParameter("login"))
                .withName(req.getParameter("name"))
                .withEmail(req.getParameter("email"))
                .withRole(Role.valueOf(req.getParameter("role")))

                .build();
        validate.update(user);
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }
}
