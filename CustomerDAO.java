package com.example.electricitybilling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.example.electricitybilling.util.DatabaseConnector;

public class CustomerDAO {

    public boolean addCustomer(String name, String address, String contactNumber, String meterNumber) {
        String insertSQL = "INSERT INTO customer (name, address, contactnumber, meternumber) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, contactNumber);
            preparedStatement.setString(4, meterNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
