package org.example.servlet;

import org.example.dto.Aviary;
import org.example.service.AviaryCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/aviary")
public class AviaryServlet extends HttpServlet {
    private AviaryCRUD aviaryCRUD = new AviaryCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<Aviary> aviaries = aviaryCRUD.readAviaries();
                req.setAttribute("aviaries", aviaries);
                req.getRequestDispatcher("read/readAviary.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            switch (action) {
                case "create":
                    int size = Integer.parseInt(req.getParameter("size"));
                    String state = req.getParameter("state");
                    aviaryCRUD.createAviary(size, state);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    size = Integer.parseInt(req.getParameter("size"));
                    state = req.getParameter("state");
                    aviaryCRUD.updateAviary(id, size, state);
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    aviaryCRUD.deleteAviary(id);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}