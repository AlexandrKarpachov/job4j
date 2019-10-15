package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 14.10.2019
 */
public class UserUpdateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        var id = Integer.parseInt(req.getParameter("id"));
        var user = this.validate.findById(new User(id, null, null, null));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<form action=" + req.getContextPath() + "/edit method='post'>\n"
                + "    <input type='hidden' name='id' value='" + user.getId() + "'/><br>\n"
                + "    <input type='hidden' name='login' value='" + user.getLogin() + "'/><br>\n"
                + "    <input type='txt' name='name' value='" + user.getName() + "'/>Name<br>\n"
                + "    <input type='txt' name='email' value='" + user.getEmail() + "'/>Email<br>\n"
                + "    <input type='txt' name='createDate"
                + "         'value='" + user.getCreateDate() + "'/>Additional Information<br>\n"
                + "    <input type='submit' value='Update'>\n"
                + "</form>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
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
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
