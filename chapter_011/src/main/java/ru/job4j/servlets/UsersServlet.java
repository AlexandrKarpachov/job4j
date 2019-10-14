package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 13.10.2019
 */
public class UsersServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder table = new StringBuilder("<table border='1'>");
        table.append("<tr><th>Id</th><th>Login</th><th>Name</th><th>Email</th></tr>");
        for (User user : validate.findAll()) {
            table.append("<tr>\n"
                    + "<td>" + user.getId() + "</td>\n"
                    + "<td>" + user.getLogin() + "</td>\n"
                    + "<td>" + user.getName() + "</td>\n"
                    + "<td>" + user.getEmail() + "</td>\n"
                    + "<td>\n"
                    + "    <form action=" + req.getContextPath() + "/edit method='get'>\n"
                    + "        <input type='hidden' name='id' value='" + user.getId() + "'/>\n"
                    + "        <input type='submit' value='Edit'>\n"
                    + "    </form>\n"
                    + "</td>\n"
                    + "<td>\n"
                    + "    <form action=" + req.getContextPath() + "/users method='post'>\n"
                    + "        <input type='hidden' name='id' value='" + user.getId() + "'/>\n"
                    + "        <input type='submit' value='Delete'>\n"
                    + "    </form>\n"
                    + "</td>\n"
                    + "</tr>");
        }
        table.append("</table>");

        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "Users list : <br>"
                + table.toString()
                + "<form action=" + req.getContextPath() + "/create method='get'>\n"
                + "<input type='submit' value='Add new User'>\n"
                + "</form>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = Integer.parseInt(req.getParameter("id"));
        this.validate.delete(new User(id, null, null, null, null));
        this.doGet(req, resp);
    }
}
