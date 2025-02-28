package org.example.servlet;

import org.example.dto.Food;
import org.example.service.FoodCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/food")
public class FoodServlet extends HttpServlet {
    private FoodCRUD foodCRUD = new FoodCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<Food> foods = foodCRUD.readFoods();
                req.setAttribute("foods", foods);
                req.getRequestDispatcher("read/readFood.jsp").forward(req, resp);
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
                    System.out.println("Servlet");
                    String name = req.getParameter("name");
                    int amount = Integer.parseInt(req.getParameter("amount"));
                    foodCRUD.createFood(name, amount);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    name = req.getParameter("name");
                    amount = Integer.parseInt(req.getParameter("amount"));
                    foodCRUD.updateFood(id, name, amount);
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    foodCRUD.deleteFood(id);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}