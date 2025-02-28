package org.example.servlet;

import org.example.dto.Species;
import org.example.service.SpeciesCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/species")
public class SpeciesServlet extends HttpServlet {
    private SpeciesCRUD speciesCRUD = new SpeciesCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<Species> speciesList = speciesCRUD.readSpecies();
                req.setAttribute("speciesList", speciesList);
                req.getRequestDispatcher("read/readSpecies.jsp").forward(req, resp);
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
                    String title = req.getParameter("title");
                    String description = req.getParameter("description");
                    speciesCRUD.createSpecies(title, description);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    title = req.getParameter("title");
                    description = req.getParameter("description");
                    speciesCRUD.updateSpecies(id, title, description);
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    speciesCRUD.deleteSpecies(id);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}