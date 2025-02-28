package org.example.servlet;

import org.example.dto.Employee;
import org.example.service.EmployeeCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeCRUD employeeCRUD = new EmployeeCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("read".equals(action)) {
                List<Employee> employees = employeeCRUD.readEmployees();
                req.setAttribute("employees", employees);
                req.getRequestDispatcher("read/readEmployee.jsp").forward(req, resp);
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
                    int salary = Integer.parseInt(req.getParameter("salary"));
                    employeeCRUD.createEmployee(name, salary);
                    break;
                case "update":
                    int id = Integer.parseInt(req.getParameter("id"));
                    name = req.getParameter("name");
                    salary = Integer.parseInt(req.getParameter("salary"));
                    employeeCRUD.updateEmployee(id, name, salary);
                    break;
                case "delete":
                    id = Integer.parseInt(req.getParameter("id"));
                    employeeCRUD.deleteEmployee(id);
                    break;
            }
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}