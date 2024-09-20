package com.example.electricitybilling.dao;

import com.example.electricitybilling.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevenueDAO {

    public double calculateTotalRevenue() {
        String sql = "SELECT SUM(amount) AS total_revenue FROM bills";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}

