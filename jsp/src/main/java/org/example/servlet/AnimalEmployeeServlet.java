package org.example.servlet;

import org.example.dto.AnimalEmployee;
import org.example.service.AnimalEmployeeCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/animalEmployee")
public class AnimalEmployeeServlet extends HttpServlet {
    private AnimalEmployeeCRUD animalEmployeeCRUD = new AnimalEmployeeCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<AnimalEmployee> animalEmployees = animalEmployeeCRUD.readAnimalEmployees();
                req.setAttribute("animalEmployees", animalEmployees);
                req.getRequestDispatcher("read/readAnimalEmployee.jsp").forward(req, resp);
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
                    int animalId = Integer.parseInt(req.getParameter("animalId"));
                    int employeeId = Integer.parseInt(req.getParameter("employeeId"));
                    animalEmployeeCRUD.createAnimalEmployee(animalId, employeeId);
                    break;
                case "delete":
                    animalId = Integer.parseInt(req.getParameter("animalId"));
                    employeeId = Integer.parseInt(req.getParameter("employeeId"));
                    animalEmployeeCRUD.deleteAnimalEmployee(animalId, employeeId);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}