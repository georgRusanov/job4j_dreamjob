package ru.job4j.dream.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.dream.helpers.Property;
import ru.job4j.dream.store.DbStore;
import ru.job4j.dream.store.Store;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        for (File file : new File(Property.getProperty("photo.dir")).listFiles()) {
            if (id.equals(file.getName())) {
                file.delete();
                break;
            }
        }
        DbStore.instOf().deleteCandidate(Integer.parseInt(id));
        req.setAttribute("candidates", DbStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}
