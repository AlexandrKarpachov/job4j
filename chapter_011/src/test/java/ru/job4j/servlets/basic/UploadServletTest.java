package ru.job4j.servlets.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.logic.Validate;
import ru.job4j.servlets.logic.ValidateService;
import ru.job4j.servlets.models.User;
import ru.job4j.servlets.logic.ValidateStub;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UploadServletTest {


    @Test
    public void whenDeleteTestUserThenEmptyStore() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("action")).thenReturn("delete");
        when(resp.getOutputStream()).thenReturn(new OutputStreamStub());
        var userServlet = new UserServlet();
        userServlet.init();

        userServlet.doPost(req, resp);

        assertThat(validate.findAll().size(), is(0));
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getParameter("action")).thenReturn("update");
        when(resp.getOutputStream()).thenReturn(new OutputStreamStub());
        var userServlet = new UserServlet();
        userServlet.init();

        userServlet.doPost(req, resp);
        User user = validate.findById(new User(0));

        assertThat(user.getName(), is("name"));
        assertThat(user.getLogin(), is("login"));
        assertThat(user.getEmail(), is("email"));
    }

    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("id")).thenReturn(null);
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("email")).thenReturn("email");
        when(req.getParameter("action")).thenReturn("add");
        when(resp.getOutputStream()).thenReturn(new OutputStreamStub());
        var userServlet = new UserServlet();
        userServlet.init();

        userServlet.doPost(req, resp);
        User user = validate.findById(new User(1));

        assertThat(user.getName(), is("name"));
        assertThat(user.getLogin(), is("login"));
        assertThat(user.getEmail(), is("email"));
    }

    static class OutputStreamStub extends ServletOutputStream {

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }

        @Override
        public void write(int b) {

        }
    }
}