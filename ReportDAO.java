package com.example.electricitybilling.dao;

import com.example.electricitybilling.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportDAO {

    // Example method for regenerating reports
    public boolean regenerateReport() {
        // Example: Delete existing reports and insert new ones
        String deleteSql = "DELETE FROM reports";
        String insertSql = "INSERT INTO reports (report_name, report_data) VALUES (?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
             PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {

            // Delete existing reports
            deleteStatement.executeUpdate();

            // Insert new reports (Example: Adjust according to your logic)
            insertStatement.setString(1, "Report Name");
            insertStatement.setString(2, "Report Data");
            int rowsInserted = insertStatement.executeUpdate();

            // Return true if at least one report was inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
  }

