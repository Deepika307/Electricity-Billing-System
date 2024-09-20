package com.example.electricitybilling.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONObject;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String URL = "jdbc:postgresql://localhost:5432/electricitybilling";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yeontan";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String meterNumber = request.getParameter("meterNumber");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        if (name == null || address == null || contactNumber == null || meterNumber == null) {
            jsonResponse.put("message", "All fields are required.");
            jsonResponse.put("success", false);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            boolean isAdded = addCustomerToDatabase(name, address, contactNumber, meterNumber);
            if (isAdded) {
                jsonResponse.put("message", "Customer added successfully.");
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("message", "Failed to add customer.");
                jsonResponse.put("success", false);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        response.getWriter().print(jsonResponse.toString());
    }

    private boolean addCustomerToDatabase(String name, String address, String contactNumber, String meterNumber) {
        String query = "INSERT INTO customer (name, address, contactnumber, meternumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contactNumber);
            ps.setString(4, meterNumber);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}