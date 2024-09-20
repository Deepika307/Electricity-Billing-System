package com.example.electricitybilling.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/BillingController")
public class BillingController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("generateReport".equals(action)) {
            generateReport(response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid action.");
        }
    }

    private void generateReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM billhistory");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Extract data from ResultSet and add it to the JSON response
                // This is a simplified example. You may need to structure your JSON response as needed
                JSONObject billRecord = new JSONObject();
                billRecord.put("date", rs.getDate("date"));
                billRecord.put("meterNumber", rs.getString("meternumber"));
                billRecord.put("unitsConsumed", rs.getInt("unitsconsumed"));
                billRecord.put("amount", rs.getDouble("amount"));

                jsonResponse.append("bills", billRecord);
            }

            response.getWriter().write(jsonResponse.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            jsonResponse.put("message", "Error generating report.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(jsonResponse.toString());
        }
    }
}
