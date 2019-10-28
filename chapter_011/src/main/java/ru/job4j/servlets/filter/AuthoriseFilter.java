package ru.job4j.servlets.filter;

import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.Role;
import ru.job4j.servlets.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 21.10.2019
 */
public class AuthoriseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var session = ((HttpServletRequest) request).getSession(false);
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        var user = ValidateService.getInstance().findByLogin(
                new User.Builder()
                        .withLogin((String) session.getAttribute("login"))
                        .build()
        );
        var param = req.getParameter("id");
        Integer id = param == null ?  null : Integer.parseInt(req.getParameter("id"));
        if (!user.getRole().equals(Role.ADMIN)
                && !user.getId().equals(id)) {
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
