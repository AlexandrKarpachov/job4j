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
public class UserCreateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<form action=" + req.getContextPath() + "/create method='post'>\n"
                + "    <input type='txt' name='name' value=''/>Name<br>\n"
                + "    <input type='txt' name='login' value=''/>Login<br>\n"
                + "    <input type='txt' name='email' value=''/>Email<br>\n"
                + "    <input type='txt' name='createDate' value=''/>Additional Information<br>\n"
                + "    <input type='submit' value='Add'>\n"
                + "</form>\n"
                + "</body>\n"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = new User(
                this.validate.generateID(),
                req.getParameter("login"),
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("createDate")
        );
        var result = this.validate.add(user);
        String answer;
        if (result) {
            answer = "You add new User";
        } else {
            answer = "Please enter correct data";
        }
        writer.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + answer
                + "</body>\n"
                + "</html>");
        writer.flush();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.doGet(req, resp);
    }
}
