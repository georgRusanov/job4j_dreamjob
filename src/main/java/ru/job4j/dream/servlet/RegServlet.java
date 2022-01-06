package ru.job4j.dream.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.DbStore;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = DbStore.instOf().findUserByEmail(email);
        if (user == null) {
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            User newUser = new User(name, email, password);
            DbStore.instOf().saveUser(newUser);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.setAttribute("error", "Пользователь с таким email уже существует");
            req.getRequestDispatcher( "reg.jsp").forward(req, resp);
        }
    }
}
