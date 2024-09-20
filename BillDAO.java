package com.example.electricitybilling.dao;

import com.example.electricitybilling.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAO {

    public boolean recordBill(String customerId, double amount) {
        String sql = "INSERT INTO billhistory (customerid, amount) VALUES (?, ?)";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customerId);
            statement.setDouble(2, amount);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
