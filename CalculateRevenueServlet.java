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
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

@WebServlet("/calculateRevenue")
public class CalculateRevenueServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String URL = "jdbc:postgresql://localhost:5432/electricitybilling";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yeontan";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        try {
            double totalRevenue = calculateTotalRevenue();
            jsonResponse.put("totalRevenue", totalRevenue);
            jsonResponse.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("message", "An error occurred while calculating total revenue.");
            jsonResponse.put("success", false);
        }

        response.getWriter().write(jsonResponse.toString());
    }

    private double calculateTotalRevenue() throws SQLException {
        double totalRevenue = 0.0;
        String query = "SELECT COALESCE(SUM(unitsconsumed * amount), 0) AS total_revenue FROM billhistory";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalRevenue = rs.getDouble("total_revenue");
            }
        }

        return totalRevenue;
    }
}
