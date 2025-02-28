package org.example.servlet;

import org.example.dto.AnimalFood;
import org.example.service.AnimalFoodCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/animalFood")
public class AnimalFoodServlet extends HttpServlet {
    private AnimalFoodCRUD animalFoodCRUD = new AnimalFoodCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<AnimalFood> animalFoods = animalFoodCRUD.readAnimalFoods();
                req.setAttribute("animalFoods", animalFoods);
                req.getRequestDispatcher("read/readAnimalFood.jsp").forward(req, resp);
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
                    int foodId = Integer.parseInt(req.getParameter("foodId"));
                    int animalId = Integer.parseInt(req.getParameter("animalId"));
                    animalFoodCRUD.createAnimalFood(foodId, animalId);
                    break;
                case "delete":
                    foodId = Integer.parseInt(req.getParameter("foodId"));
                    animalId = Integer.parseInt(req.getParameter("animalId"));
                    animalFoodCRUD.deleteAnimalFood(foodId, animalId);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}