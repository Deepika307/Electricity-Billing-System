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

@WebServlet("/recordBill")
public class RecordBillServlet extends HttpServlet {

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
        String date = request.getParameter("date");
        String meterNumber = request.getParameter("meternumber");
        String unitsConsumedStr = request.getParameter("unitsconsumed");
        String amountStr = request.getParameter("amount");

        try {
            int unitsConsumed = Integer.parseInt(unitsConsumedStr);
            double amount = Double.parseDouble(amountStr);

            recordBillToDatabase(date, meterNumber, unitsConsumed, amount);
            response.getWriter().write("Bill recorded successfully.");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Failed to record bill.");
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void recordBillToDatabase(String date, String meterNumber, int unitsConsumed, double amount) throws SQLException {
        String query = "INSERT INTO billhistory (date, meternumber, unitsconsumed, amount) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDate(1, java.sql.Date.valueOf(date));
            ps.setString(2, meterNumber);
            ps.setInt(3, unitsConsumed);
            ps.setDouble(4, amount);

            ps.executeUpdate();
        }
    }
}
