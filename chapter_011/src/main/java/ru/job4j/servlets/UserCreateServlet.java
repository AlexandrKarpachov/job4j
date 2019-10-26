package ru.job4j.servlets;


import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Aleksandr Karpachov
 * @version 1.0
 * @since 14.10.2019
 */
@MultipartConfig
public class UserCreateServlet extends HttpServlet {
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("WEB-INF/views/Create.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        File folder = new File("images");
        if (!folder.exists()) {
            //noinspection ResultOfMethodCallIgnored
            folder.mkdir();
        }
        User user = new User.Builder()
                .withID(validate.generateID())
                .withLogin(req.getParameter("login"))
                .withName(req.getParameter("name"))
                .withEmail(req.getParameter("email"))
                .withPassword(req.getParameter("password"))
                .build();
        Part item = req.getPart("file");
        if (item.getSize() > 0) {
            String fileName = Paths.get(item.getSubmittedFileName()).getFileName().toString();
            File file = new File(folder + File.separator + fileName);
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(item.getInputStream().readAllBytes());
            }
            user.setPhotoId(fileName);
        }
        var result = this.validate.add(user);
        if (result) {
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/create", req.getContextPath()));
        }
    }
}
