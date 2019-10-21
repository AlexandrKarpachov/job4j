package ru.job4j.servlets.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 20.10.2019
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var session = ((HttpServletRequest) request).getSession(false);
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        if (req.getRequestURI().contains("/signIn") || req.getRequestURI().contains("/create")) {
            chain.doFilter(request, response);
        } else if (session == null || session.getAttribute("login") == null) {
            resp.sendRedirect(String.format("%s/signIn", req.getContextPath()));
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
