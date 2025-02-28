package org.example.servlet;

import org.example.dto.Animal;
import org.example.service.AnimalCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/animal")
public class AnimalServlet extends HttpServlet {
    private AnimalCRUD animalCRUD = new AnimalCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<Animal> animals = animalCRUD.readAnimals();
                req.setAttribute("animals", animals);
                req.getRequestDispatcher("read/readAnimal.jsp").forward(req, resp);
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
                    String name = req.getParameter("name");
                    String gender = req.getParameter("gender");
                    int age = Integer.parseInt(req.getParameter("age"));
                    int aviaryId = Integer.parseInt(req.getParameter("aviaryId"));
                    int speciesId = Integer.parseInt(req.getParameter("speciesId"));
                    animalCRUD.createAnimal(name, gender, age, aviaryId, speciesId);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    name = req.getParameter("name");
                    gender = req.getParameter("gender");
                    age = Integer.parseInt(req.getParameter("age"));
                    aviaryId = Integer.parseInt(req.getParameter("aviaryId"));
                    speciesId = Integer.parseInt(req.getParameter("speciesId"));
                    animalCRUD.updateAnimal(id, name, gender, age, aviaryId, speciesId);
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    animalCRUD.deleteAnimal(id);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}