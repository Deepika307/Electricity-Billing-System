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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/regenerateReport")
public class RegenerateReportServlet extends HttpServlet {

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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            List<BillRecord> billRecords = getBillHistory();
            StringBuilder htmlResponse = new StringBuilder("<table border='1'><tr><th>Date</th><th>Meter Number</th><th>Units Consumed</th><th>Amount</th></tr>");
            for (BillRecord record : billRecords) {
                htmlResponse.append("<tr>")
                        .append("<td>").append(record.getDate()).append("</td>")
                        .append("<td>").append(record.getMeterNumber()).append("</td>")
                        .append("<td>").append(record.getUnitsConsumed()).append("</td>")
                        .append("<td>").append(record.getAmount()).append("</td>")
                        .append("</tr>");
            }
            htmlResponse.append("</table>");
            response.getWriter().write(htmlResponse.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Failed to regenerate report.");
        }
    }

    private List<BillRecord> getBillHistory() throws SQLException {
        List<BillRecord> billRecords = new ArrayList<>();
        String query = "SELECT * FROM billhistory";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BillRecord record = new BillRecord(
                        rs.getDate("date").toString(),
                        rs.getString("meternumber"),
                        rs.getInt("unitsconsumed"),
                        rs.getDouble("amount")
                );
                billRecords.add(record);
            }
        }
        return billRecords;
    }

    private static class BillRecord {
        private final String date;
        private final String meterNumber;
        private final int unitsConsumed;
        private final double amount;

        public BillRecord(String date, String meterNumber, int unitsConsumed, double amount) {
            this.date = date;
            this.meterNumber = meterNumber;
            this.unitsConsumed = unitsConsumed;
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public String getMeterNumber() {
            return meterNumber;
        }

        public int getUnitsConsumed() {
            return unitsConsumed;
        }

        public double getAmount() {
            return amount;
        }
    }
}
